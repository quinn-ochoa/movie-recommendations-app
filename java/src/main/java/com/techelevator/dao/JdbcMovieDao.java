package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Movie;
import com.techelevator.model.MovieApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMovieDao implements MovieDao{

    private final JdbcTemplate jdbcTemplate;

    public JdbcMovieDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean isMovieInDatabase(int movieId){

        String sql = "SELECT EXISTS (SELECT 1 FROM movies WHERE id = ? LIMIT 1);";
        boolean movieExists;

        try{

            movieExists = jdbcTemplate.queryForObject(sql, boolean.class, movieId);

        } catch (CannotGetJdbcConnectionException e){

            throw new DaoException("Unable to connect to server or database", e);

        } catch (DataIntegrityViolationException e){

            throw new DaoException("Data integrity violation", e);

        } return movieExists;

    }

    @Override
    public void addMovie(Movie movie) {

        String sql = "INSERT INTO movies (id, title, overview, poster_path, vote_average)" +
                " VALUES (?,?,?,?,?);";

        try{

            jdbcTemplate.update(sql, movie.getId(), movie.getTitle(), movie.getOverview(), movie.getPoster_path(), movie.getVote_average());

        } catch (CannotGetJdbcConnectionException e){

            throw new DaoException("Unable to connect to server or database", e);

        } catch (DataIntegrityViolationException e){

            throw new DaoException("Data integrity violation", e);

        }

    }

    @Override
    public void markMovieAsDoNotShow(int movie_id) {

        String sql = "UPDATE movies SET do_not_show = true WHERE id = ?;";

        try {

            jdbcTemplate.update(sql, movie_id);

        } catch (CannotGetJdbcConnectionException e) {

            throw new DaoException("Unable to connect to server or database", e);

        } catch (DataIntegrityViolationException e) {

            throw new DaoException("Data integrity violation", e);

        }

    }

    @Override
    public MovieApiResponse getFavoriteMovies(int user_id) {

        MovieApiResponse movieApiResponse = new MovieApiResponse();
        Movie movie;
        String sql = "SELECT * FROM movies WHERE id in (SELECT movie_id FROM movies_users WHERE user_id = ? AND liked = true);";

        try {

            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, user_id);

            while (result.next()){

                movie = mapRowToMovie(result);
                movieApiResponse.getResults().add(movie);

            }

        } catch (CannotGetJdbcConnectionException e){

            throw new DaoException("Unable to connect to server or database");

        } return movieApiResponse;

    }

    @Override
    public List<Movie> getAllMoviesReviewedByUser(int userId) {

        String sql = "SELECT * FROM movies WHERE id in (SELECT movie_id FROM movies_users WHERE user_id = ?);";
        List<Movie> moviesReviewed = new ArrayList<>();

        try {

            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userId);

            while (result.next()){

                moviesReviewed.add(mapRowToMovie(result));

            }

        } catch (CannotGetJdbcConnectionException e){

            throw new DaoException("Unable to connect to server or database");

        } return moviesReviewed;

    }

    @Override
    public MovieApiResponse addGenreNameToResponse(MovieApiResponse movieApiResponse) {

        String sql = "SELECT name FROM genres WHERE id = ?;";
        String currentSelectedGenreName;

        for (Movie movie : movieApiResponse.getResults()) {

            for (Integer genre_id : movie.getGenre_ids()) {

                try{

                    currentSelectedGenreName = jdbcTemplate.queryForObject(sql, String.class, genre_id);
                    movie.getGenre_names().add(currentSelectedGenreName);

                } catch (CannotGetJdbcConnectionException e){

                    throw new DaoException("Unable to connect to server or database", e);

                } catch (DataIntegrityViolationException e){

                    throw new DaoException("Data integrity violation", e);

                }

            }

        } return movieApiResponse;

    }

    @Override
    public Movie getMovieById(int movie_id) {

        String sql = "SELECT * FROM movies WHERE id = ?;";
        Movie movie = new Movie();

        try {

            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, movie_id);

            while (result.next()){

                movie = mapRowToMovie(result);

            }

        } catch (CannotGetJdbcConnectionException e){

            throw new DaoException("Unable to connect to server or database");

        } return movie;

    }

    @Override
    public Movie addGenreNameToMovie(Movie movie) {

        String sql = "SELECT name FROM genres WHERE id = ?;";
        String currentSelectedGenreName;

        for (Integer genre_id : movie.getGenre_ids()) {

            try{

                currentSelectedGenreName = jdbcTemplate.queryForObject(sql, String.class, genre_id);
                movie.getGenre_names().add(currentSelectedGenreName);

            } catch (CannotGetJdbcConnectionException e){

                throw new DaoException("Unable to connect to server or database", e);

            } catch (DataIntegrityViolationException e){

                throw new DaoException("Data integrity violation", e);

            }

        } return movie;

    }

    private Movie mapRowToMovie(SqlRowSet result) {

        Movie movie = new Movie();
        movie.setTitle(result.getString("title"));
        movie.setOverview(result.getString("overview"));
        movie.setPoster_path(result.getString("poster_path"));
        movie.setVote_average(result.getBigDecimal("vote_average"));
        movie.setId(result.getInt("id"));
        movie.setBadName(result.getBoolean("do_not_show"));
        return movie;

    }

}


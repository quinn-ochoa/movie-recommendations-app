package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Genres;
import com.techelevator.model.Movie;
import com.techelevator.model.MovieApiResponse;
import com.techelevator.services.ProfanityFilterService;
import com.techelevator.services.TMDBService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

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

        JdbcMovieGenreDao jdbcMovieGenreDao = new JdbcMovieGenreDao(jdbcTemplate);
        String sql = "INSERT INTO movies (id, title, overview, poster_path, vote_average)" +
                " VALUES (?,?,?,?,?);";

        try{

            jdbcTemplate.update(sql, movie.getId(), movie.getTitle(), movie.getOverview(), movie.getPoster_path(), movie.getVote_average());
            jdbcMovieGenreDao.updateMovieGenreAssociation(movie.getGenre_ids(), movie.getId());

        } catch (CannotGetJdbcConnectionException e){

            throw new DaoException("Unable to connect to server or database", e);

        } catch (DataIntegrityViolationException e){

            throw new DaoException("Data integrity violation", e);

        }

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
    public MovieApiResponse throwOutBadMovies(MovieApiResponse movieApiResponse) {

        ProfanityFilterService profanityFilterService = new ProfanityFilterService();
        MovieApiResponse filtered = profanityFilterService.checkForProfaneTitle(movieApiResponse);
        filtered.setTotal_pages(movieApiResponse.getTotal_pages());
        return filtered;

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
        return movie;

    }

}


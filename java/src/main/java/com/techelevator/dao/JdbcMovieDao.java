package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Movie;
import com.techelevator.model.MovieApiResponse;
import com.techelevator.services.TMDBService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcMovieDao implements MovieDao{

    private final JdbcTemplate jdbcTemplate;
    private final TMDBService tmdbService;

    public JdbcMovieDao(JdbcTemplate jdbcTemplate, TMDBService service) {
        this.tmdbService = service;
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

}


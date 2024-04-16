package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Genres;
import com.techelevator.model.MoviesUsers;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcMoviesUsersDao implements MoviesUsersDao{
    //properties
    private final JdbcTemplate jdbcTemplate;

    public JdbcMoviesUsersDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    //constructors

    //methods
    @Override
    public void updateMoviesUsers(MoviesUsers moviesUsers) {

        String sql = "UPDATE movies_users SET liked = ?, review = ? WHERE movie_id = ? AND user_id = ?;";

        try {

            jdbcTemplate.update(sql, moviesUsers.isLiked(), moviesUsers.getReview(), moviesUsers.getMovie_id(), moviesUsers.getUser_id());

        } catch (CannotGetJdbcConnectionException e) {

            throw new DaoException("Unable to connect to server or database", e);

        } catch (DataIntegrityViolationException e) {

            throw new DaoException("Data integrity violation", e);

        }

    }

    @Override
    public boolean checkForMovieUserAssociation(MoviesUsers moviesUsers) {

        String sql = "SELECT EXISTS (SELECT 1 FROM movies_users WHERE movie_id = ? AND user_id = ? LIMIT 1);";
        boolean moviesUsersExists;

        try{

            moviesUsersExists = jdbcTemplate.queryForObject(sql, boolean.class, moviesUsers.getMovie_id(), moviesUsers.getUser_id());

        } catch (CannotGetJdbcConnectionException e){

            throw new DaoException("Unable to connect to server or database", e);

        } catch (DataIntegrityViolationException e){

            throw new DaoException("Data integrity violation", e);

        } return moviesUsersExists;

    }

    @Override
    public void addMoviesUsers(MoviesUsers moviesUsers) {

        String sql = "INSERT INTO movies_users (movie_id, user_id, liked, review)" +
                " VALUES (?,?,?,?);";

        try{

            jdbcTemplate.update(sql, moviesUsers.getMovie_id(), moviesUsers.getUser_id(), moviesUsers.isLiked(), moviesUsers.getReview());

        } catch (CannotGetJdbcConnectionException e){

            throw new DaoException("Unable to connect to server or database", e);

        } catch (DataIntegrityViolationException e){

            throw new DaoException("Data integrity violation", e);

        }

    }

    @Override
    public String getUserReviewOfMovie(int userId, int movieId) {

        String sql = "SELECT review FROM movies_users WHERE movie_id = ? AND user_id = ?;";
        String review;

        try{

            review = jdbcTemplate.queryForObject(sql, String.class, movieId, userId);

        } catch (CannotGetJdbcConnectionException e){

            throw new DaoException("Unable to connect to server or database", e);

        } catch (DataIntegrityViolationException e){

            throw new DaoException("Data integrity violation", e);

        } return review;

    }

    @Override
    public int getUserReviewIdOfMovie(int userId, int movieId) {

        String sql = "SELECT review_id FROM movies_users WHERE movie_id = ? AND user_id = ?;";
        int review_id;

        try{

            review_id = jdbcTemplate.queryForObject(sql, int.class, movieId, userId);

        } catch (CannotGetJdbcConnectionException e){

            throw new DaoException("Unable to connect to server or database", e);

        } catch (DataIntegrityViolationException e){

            throw new DaoException("Data integrity violation", e);

        } return review_id;

    }

}

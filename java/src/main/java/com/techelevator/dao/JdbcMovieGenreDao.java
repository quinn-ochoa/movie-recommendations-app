package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JdbcMovieGenreDao implements MovieGenreDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcMovieGenreDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void updateMovieGenreAssociation(List<Integer> genreIds, int movieId) {

        String sql = "INSERT INTO movies_genres (movie_id, genre_id)" +
                " VALUES (?,?);";

        for (Integer genreId: genreIds) {

            try{

                jdbcTemplate.update(sql, movieId, genreId);

            } catch (CannotGetJdbcConnectionException e){

                throw new DaoException("Unable to connect to server or database", e);

            } catch (DataIntegrityViolationException e){

                throw new DaoException("Data integrity violation", e);

            }

        }

    }

}

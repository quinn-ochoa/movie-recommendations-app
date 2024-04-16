package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcMovieCertificationDao implements MovieCertificationDao {
    //properties
    JdbcTemplate jdbcTemplate;

    //constructors
    public JdbcMovieCertificationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //methods
    @Override
    public void updateMovieCertificationAssociation(int rating, int movie_id) {

        String sql = "INSERT INTO movies_certifications (certification_id, movie_id) VALUES (?,?);";

        try {

            jdbcTemplate.update(sql, rating, movie_id);

        } catch (CannotGetJdbcConnectionException e){

            throw new DaoException("Unable to connect to server or database", e);

        } catch (DataIntegrityViolationException e){

            throw new DaoException("Data integrity violation", e);

        }

    }

    @Override
    public int getCertificationByMovieId(int movie_id) {

        String sql = "SELECT certification_id FROM movies_certifications WHERE movie_id = ?;";
        Integer certificationCode;

        try{

            certificationCode = jdbcTemplate.queryForObject(sql, Integer.class, movie_id);

        } catch (CannotGetJdbcConnectionException e){

            throw new DaoException("Unable to connect to server or database", e);

        } catch (DataIntegrityViolationException e){

            throw new DaoException("Data integrity violation", e);

        }
        return certificationCode;
    }

}

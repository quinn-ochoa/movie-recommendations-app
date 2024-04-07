package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Genres;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcGenresDao implements GenresDao{
    //properties
    private final JdbcTemplate jdbcTemplate;

    //constructors
    public JdbcGenresDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //methods
    @Override
    public List<Genres> getAllGenres() {

        String sql = "SELECT * FROM genres;";
        List<Genres> genres = new ArrayList<>();

        try {

            SqlRowSet result = jdbcTemplate.queryForRowSet(sql);

            while (result.next()){

                genres.add(mapRowToGenres(result));

            }

        } catch (CannotGetJdbcConnectionException e){

            throw new DaoException("Unable to connect to server or database");

        } return genres;

    }

    @Override
    public int getGenreIdByGenreName(String name) {

        String sql = "SELECT id FROM genres WHERE name = ?;";
        int id;

        try{

            id = jdbcTemplate.queryForObject(sql, int.class, name);

        } catch (CannotGetJdbcConnectionException e){

            throw new DaoException("Unable to connect to server or database", e);

        } catch (DataIntegrityViolationException e){

            throw new DaoException("Data integrity violation", e);

        } return id;

    }


    private Genres mapRowToGenres(SqlRowSet result) {

        Genres genres = new Genres();
        genres.setId(result.getInt("id"));
        genres.setName(result.getString("name"));
        return genres;
    }
}

package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Genres;
import com.techelevator.model.UsersGenres;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class JdbcUsersGenresDao implements UsersGenresDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcUsersGenresDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Map<String, Boolean> getGenresByUserId(int user_id) {

        List<UsersGenres> genres = new ArrayList<>();
        String sql = "SELECT name FROM genres WHERE id = (SELECT genre_id FROM users_genres WHERE user_id = ?);";

        try {

            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, user_id);

            while (result.next()){

                genres.add(mapRowToUsersGenres(result));

            }

        }catch (CannotGetJdbcConnectionException e){

            throw new DaoException("Unable to connect to server or database");

        } return mapUserGenres(genres);

    }

    private Map<String, Boolean> mapUserGenres(List<UsersGenres> genres) {

        JdbcGenresDao jdbcGenresDao = new JdbcGenresDao(jdbcTemplate);
        List<Genres> allGenres = jdbcGenresDao.getAllGenres();
        Map<String, Boolean> allGenresState = new HashMap<>();
        String sql = "SELECT name FROM genres WHERE id = ?;";
        String currentGenre;

        for (Genres genre : allGenres) {

            allGenresState.put(genre.getName(), false);

        } for (UsersGenres genre : genres) {

            try{

                currentGenre = jdbcTemplate.queryForObject(sql, String.class, genre.getGenre_id());
                allGenresState.put(currentGenre, true);

            } catch (CannotGetJdbcConnectionException e){

                throw new DaoException("Unable to connect to server or database", e);

            } catch (DataIntegrityViolationException e){

                throw new DaoException("Data integrity violation", e);

            }

        } return allGenresState;

    }

    private UsersGenres mapRowToUsersGenres(SqlRowSet result) {

        UsersGenres usersGenres = new UsersGenres();
        usersGenres.setUser_id(result.getInt("user_id"));
        usersGenres.setGenre_id(result.getInt("genre_id"));

        return usersGenres;
    }

}

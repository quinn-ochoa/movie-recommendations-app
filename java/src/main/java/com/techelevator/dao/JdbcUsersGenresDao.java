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

        List<Genres> genres = new ArrayList<>();
        String sql = "SELECT * FROM genres WHERE id = (SELECT genre_id FROM users_genres WHERE user_id = ?);";

        try {

            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, user_id);

            while (result.next()){


                genres.add(mapRowToGenres(result));

            }

        }catch (CannotGetJdbcConnectionException e){

            throw new DaoException("Unable to connect to server or database");

        } return mapUserGenres(genres);

    }

    private Map<String, Boolean> mapUserGenres(List<Genres> favoriteGenres) {

        JdbcGenresDao jdbcGenresDao = new JdbcGenresDao(jdbcTemplate);
        List<Genres> allGenres = jdbcGenresDao.getAllGenres();
        Map<String, Boolean> allGenresState = new HashMap<>();

        for (Genres genre : allGenres) {

            allGenresState.put(genre.getName(), false);

        } for (Genres genre : favoriteGenres) {

            allGenresState.put(genre.getName(), true);

        } return allGenresState;

    }

    private Genres mapRowToGenres(SqlRowSet result) {

        Genres genres = new Genres();
        genres.setId(result.getInt("id"));
        genres.setName(result.getString("name"));

        return genres;
    }

}

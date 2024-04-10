package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Genres;
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

    //properties
    private final JdbcTemplate jdbcTemplate;

    //constructors
    public JdbcUsersGenresDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //methods
    @Override
    public Map<String, Boolean> getGenresByUserId(int user_id) {

        List<Genres> genres = new ArrayList<>();
        String sql = "SELECT * FROM genres WHERE id in (SELECT genre_id FROM users_genres WHERE user_id = ?);";

        try {

            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, user_id);

            while (result.next()){


                genres.add(mapRowToGenres(result));

            }

        }catch (CannotGetJdbcConnectionException e){

            throw new DaoException("Unable to connect to server or database");

        } return mapUserGenres(genres);

    }

    @Override
    public void setUsersGenresAssociations(int user_id, Map<String, Boolean> favoriteGenres) {

        String sql = "INSERT INTO users_genres (user_id, genre_id) VALUES (?,?);";
        deleteAllFavoriteGenresByUserId(user_id);
        JdbcGenresDao jdbcGenresDao = new JdbcGenresDao(jdbcTemplate);

        for (Map.Entry<String, Boolean> favoriteGenre: favoriteGenres.entrySet()) {

            if (favoriteGenre.getValue()) {

                try {

                    jdbcTemplate.update(sql, user_id, jdbcGenresDao.getGenreIdByGenreName(favoriteGenre.getKey()));

                } catch (CannotGetJdbcConnectionException e) {

                    throw new DaoException("Unable to connect to server or database", e);

                } catch (DataIntegrityViolationException e) {

                    throw new DaoException("Data integrity violation", e);

                }

            }

        }

    }

    @Override
    public void deleteAllFavoriteGenresByUserId(int id) {

        String sql = "DELETE FROM users_genres WHERE user_id = ?;";

        try{

            jdbcTemplate.update(sql, id);

        } catch (CannotGetJdbcConnectionException e){

            throw new DaoException("Unable to connect to server or database", e);

        } catch (DataIntegrityViolationException e){

            throw new DaoException("Data integrity violation", e);

        }

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

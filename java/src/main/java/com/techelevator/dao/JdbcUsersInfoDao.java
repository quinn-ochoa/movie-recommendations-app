package com.techelevator.dao;

import com.techelevator.dao.UsersInfoDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.User;
import com.techelevator.model.UsersInfo;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class JdbcUsersInfoDao implements UsersInfoDao {

    //properties
    private final JdbcTemplate jdbcTemplate;

    //constructors
    public JdbcUsersInfoDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //methods
    @Override
    public void addProfileInfo(UsersInfo usersInfo) {

        String sql = "INSERT INTO users_info (user_id, email, full_name, birthday) VALUES (?,?,?,?);";

        try{

            jdbcTemplate.update(sql, usersInfo.getUser_id(), usersInfo.getEmail(), usersInfo.getFull_name(), usersInfo.getBirthday());

        } catch (CannotGetJdbcConnectionException e){

            throw new DaoException("Unable to connect to server or database", e);

        } catch (DataIntegrityViolationException e){

            throw new DaoException("Data integrity violation", e);

        }

    }

    @Override
    public void updateProfileInfo(UsersInfo usersInfo) {

        String sql = "UPDATE users_info SET email = ?, full_name = ?, birthday = ? WHERE user_id = ?;";

        try {

            jdbcTemplate.update(sql, usersInfo.getEmail(), usersInfo.getFull_name(), usersInfo.getBirthday(), usersInfo.getUser_id());

        } catch (CannotGetJdbcConnectionException e) {

            throw new DaoException("Unable to connect to server or database", e);

        } catch (DataIntegrityViolationException e) {

            throw new DaoException("Data integrity violation", e);

        }

    }

    @Override
    public UsersInfo getUserInfo(int user_id) {

        UsersInfo usersInfo = null;
        String sql = "SELECT * FROM users_info WHERE user_id = ?";

        try {

            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, user_id);

            if (results.next()) {

                usersInfo = mapRowToUser(results);

            }

        } catch (CannotGetJdbcConnectionException e) {

            throw new DaoException("Unable to connect to server or database", e);

        } return usersInfo;

    }

    private UsersInfo mapRowToUser(SqlRowSet results) {
        UsersInfo usersInfo = new UsersInfo();
        usersInfo.setUser_id(results.getInt("user_id"));
        usersInfo.setEmail(results.getString("email"));
        usersInfo.setFull_name(results.getString("full_name"));
        usersInfo.setBirthday(results.getTimestamp("birthday"));
        return usersInfo;
    }

}

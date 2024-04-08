package com.techelevator.model;

import com.techelevator.exception.DaoException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcUsersInfoDao implements UsersInfoDao{

    //properties
    private final JdbcTemplate jdbcTemplate;

    //constructors
    public JdbcUsersInfoDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //methods
    @Override
    public void addProfileInfo(UsersInfo usersInfo) {
//TODO Working here
        String sql = "INSERT INTO users_info (user_id, email, full_name, birthday) VALUES (?,?,?,?);";

        try{

            jdbcTemplate.update(sql, usersInfo.getUser_id(), usersInfo.getEmail(), usersInfo.getFull_name(), usersInfo.getBirthday());

        } catch (CannotGetJdbcConnectionException e){

            throw new DaoException("Unable to connect to server or database", e);

        } catch (DataIntegrityViolationException e){

            throw new DaoException("Data integrity violation", e);

        }
    }
}

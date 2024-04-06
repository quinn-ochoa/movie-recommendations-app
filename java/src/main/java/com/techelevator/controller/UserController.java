package com.techelevator.controller;

import com.techelevator.dao.JdbcUsersGenresDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
public class UserController {
    //properties

    //constructors
    public UserController() {
    }

    //methods
    @RequestMapping(path = "/user/{id}/favorite/genres", method = RequestMethod.GET)
    public Map<String, Boolean> getUserFavoriteGenres(@PathVariable int id) {

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        JdbcUsersGenresDao jdbcUsersGenresDao = new JdbcUsersGenresDao(jdbcTemplate);
        return jdbcUsersGenresDao.getGenresByUserId(id);

    }

}

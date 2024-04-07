package com.techelevator.controller;

import com.techelevator.dao.JdbcUsersGenresDao;
import com.techelevator.dao.UsersGenresDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
public class UserController {
    //properties
    UsersGenresDao usersGenresDao;
    //constructors
    public UserController(UsersGenresDao usersGenresDao) {
        this.usersGenresDao = usersGenresDao;
    }

    //methods
    @RequestMapping(path = "/user/{id}/favorite/genres", method = RequestMethod.GET)
    public Map<String, Boolean> getUserFavoriteGenres(@PathVariable int id) {

        return usersGenresDao.getGenresByUserId(id);

    }

}

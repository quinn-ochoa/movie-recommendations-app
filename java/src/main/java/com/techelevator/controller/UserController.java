package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.dao.UsersGenresDao;
import com.techelevator.model.UsersInfo;
import com.techelevator.model.UsersInfoDao;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
public class UserController {
    //properties
    UsersGenresDao usersGenresDao;
    UsersInfoDao usersInfoDao;
    UserDao userDao;
    //constructors
    public UserController(UsersGenresDao usersGenresDao, UsersInfoDao usersInfoDao, UserDao userDao) {
        this.usersGenresDao = usersGenresDao;
        this.usersInfoDao = usersInfoDao;
        this.userDao =userDao;
    }

    //methods
    @RequestMapping(path = "/user/{id}/favorite/genres", method = RequestMethod.GET)
    public Map<String, Boolean> getUserFavoriteGenres(@PathVariable int user_id) {

        return usersGenresDao.getGenresByUserId(user_id);

    }

    @RequestMapping(path = "/user/profile/update/{info}", method = RequestMethod.PUT)
    public void addUserProfileInfo(@RequestBody @PathVariable UsersInfo usersInfo) {

        usersInfo.setUser_id(userDao.getIdByUsername(usersInfo.getUsername()));
        usersInfoDao.addProfileInfo(usersInfo);
        usersGenresDao.setUsersGenresAssociations(usersInfo.getUser_id(), usersInfo.getFavoriteGenres());

    }

}

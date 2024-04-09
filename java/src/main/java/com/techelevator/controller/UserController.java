package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.dao.UsersGenresDao;
import com.techelevator.model.UsersInfo;
import com.techelevator.dao.UsersInfoDao;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @RequestMapping(path = "/user/{user_id}/favorite/genres/", method = RequestMethod.GET)
    public Map<String, Boolean> getUserFavoriteGenres(@PathVariable int user_id) {

        return usersGenresDao.getGenresByUserId(user_id);

    }

    @RequestMapping(path = "/user/profile/create/", method = RequestMethod.POST)
    public void addUserProfileInfo(@Valid @RequestBody UsersInfo usersInfo) {

        usersInfo.setUser_id(userDao.getIdByUsername(usersInfo.getUsername()));
        usersInfoDao.addProfileInfo(usersInfo);
        usersGenresDao.setUsersGenresAssociations(usersInfo.getUser_id(), usersInfo.getFavoriteGenres());

    }

}

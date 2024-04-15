package com.techelevator.controller;

import com.techelevator.dao.*;
import com.techelevator.model.Movie;
import com.techelevator.model.MovieApiResponse;
import com.techelevator.model.MoviesUsers;
import com.techelevator.model.UsersInfo;
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
    MoviesUsersDao moviesUsersDao;
    MovieDao movieDao;
    MovieGenreDao movieGenreDao;

    //constructors
    public UserController(UsersGenresDao usersGenresDao, UsersInfoDao usersInfoDao, UserDao userDao, MoviesUsersDao moviesUsersDao, MovieDao movieDao, MovieGenreDao movieGenreDao) {
        this.usersGenresDao = usersGenresDao;
        this.usersInfoDao = usersInfoDao;
        this.userDao = userDao;
        this.moviesUsersDao = moviesUsersDao;
        this.movieDao = movieDao;
        this.movieGenreDao = movieGenreDao;
    }

    //methods
    @RequestMapping(path = "/user/{user_id}/favorite/genres/", method = RequestMethod.GET)
    public Map<String, Boolean> getUserFavoriteGenres(@PathVariable int user_id) {

        return usersGenresDao.getGenresByUserId(user_id);

    }

    @RequestMapping(path = "/user/{user_id}/profile/", method = RequestMethod.GET)
    public UsersInfo getUserProfileInfo(@PathVariable int user_id) {

        UsersInfo usersInfo = usersInfoDao.getUserInfo(user_id);
        usersInfo.setFavoriteGenres(usersGenresDao.getGenresByUserId(user_id));
        return usersInfo;

    }

    @RequestMapping(path = "/user/profile/create/", method = RequestMethod.POST)
    public void addUserProfileInfo(@Valid @RequestBody UsersInfo usersInfo) {

        usersInfo.setUser_id(userDao.getIdByUsername(usersInfo.getUsername()));
        usersInfoDao.addProfileInfo(usersInfo);
        usersGenresDao.setUsersGenresAssociations(usersInfo.getUser_id(), usersInfo.getFavoriteGenres());

    }

    @RequestMapping(path = "/user/profile/update/", method = RequestMethod.PUT)
    public void updateUserProfileInfo(@Valid @RequestBody UsersInfo usersInfo) {

        usersInfo.setUser_id(userDao.getIdByUsername(usersInfo.getUsername()));
        usersInfoDao.updateProfileInfo(usersInfo);
        usersGenresDao.setUsersGenresAssociations(usersInfo.getUser_id(), usersInfo.getFavoriteGenres());

    }

    @RequestMapping(path = "/user/movie/favorite/", method = RequestMethod.POST)
    public void updateUserMovieOpinions(@Valid @RequestBody MoviesUsers moviesUsers) {

        if (!moviesUsersDao.checkForMovieUserAssociation(moviesUsers)) {

            moviesUsersDao.addMoviesUsers(moviesUsers);

        } else {

            moviesUsersDao.updateMoviesUsers(moviesUsers);

        }

    }

    @RequestMapping(path = "/user/{user_id}/movie/favorites/",  method = RequestMethod .GET)
    public MovieApiResponse getAllFavoriteMovies(@Valid @PathVariable int user_id) {

        MovieApiResponse moviesReturned = movieDao.getFavoriteMovies(user_id);

        for (Movie movie : moviesReturned.getResults()) {

            movie.setGenre_ids(movieGenreDao.getGenreIdsByMovieId(movie.getId()));

        } return moviesReturned;

    }

    @RequestMapping(path = "/user/{user_id}/movie/{movie_id}/review/", method = RequestMethod.GET)
    public String getUsersReviewOfAMovie(@PathVariable int user_id, @PathVariable int movie_id) {
        return moviesUsersDao.getUserReviewOfMovie(user_id, movie_id);
    }

}

package com.techelevator.dao;

import java.util.Map;

public interface UsersGenresDao {

    Map<String, Boolean> getGenresByUserId(int user_id);

    void setUsersGenresAssociations(int id, Map<String, Boolean> favoriteGenres);

    void deleteAllFavoriteGenresByUserId(int id);
}

package com.techelevator.dao;

import java.util.Map;

public interface UsersGenresDao {

    Map<String, Boolean> getGenresByUserId(int user_id);
}

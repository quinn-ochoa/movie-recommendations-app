package com.techelevator.dao;

import com.techelevator.model.UsersInfo;

public interface UsersInfoDao {

    void addProfileInfo(UsersInfo usersInfo);

    void updateProfileInfo(UsersInfo usersInfo);

    UsersInfo getUserInfo(int user_id);
}

package com.andrewjunggg;

public interface IDataManager {
    Group getRootGroup();

    Group findGroupById(String id);

    User findUserById(String id);

    User[] getAllUsers();

    Group[] getAllGroups();

    Tweet[] getAllTweets();
}
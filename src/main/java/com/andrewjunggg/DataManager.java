package com.andrewjunggg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DataManager implements IDataManager {
    private static DataManager instance;
    private final Group rootGroup = new Group("Root");

    private DataManager() {
    }

    public static DataManager getInstance() {
        if (instance == null)
            instance = new DataManager();
        
        return instance;
    }

    // Group
    @Override
    public Group getRootGroup() {
        return rootGroup;
    }

    @Override
    public Group[] getAllGroups() {
        return getAllGroupsRecursively(rootGroup);
    }

    private Group[] getAllGroupsRecursively(Group group) {
        List<Group> groups = new ArrayList<>();
        groups.add(group);

        Group[] subgroups = group.getSubgroupsArray();

        for (Group subgroup : subgroups) {
            Collections.addAll(groups, getAllGroupsRecursively(subgroup));
        }

        return groups.toArray(Group[]::new);
    }

    private Group findGroupByIdRecursively(String idString, Group root) {
        if (root.getId().equals(idString)) {
            return root;
        }

        for (Group subgroup : root.getSubgroupsArray()) {
            Group result = findGroupByIdRecursively(idString, subgroup);
            if (result != null)
                return result;
        }

        return null;
    }

    @Override
    public Group findGroupById(String id) {
        Group rootGroup = getRootGroup();
        return findGroupByIdRecursively(id, rootGroup);
    }

    // User
    @Override
    public User findUserById(String id) {
        Group rootGroup = getRootGroup();
        return recursivelyFindUserById(id, rootGroup);
    }

    private User recursivelyFindUserById(String id, Group root) {
        for (User user : root.getUsersArray()) {
            if (user.getId().equals(id))
                return user;
        }

        for (Group subgroup : root.getSubgroupsArray()) {
            User result = recursivelyFindUserById(id, subgroup);
            if (result != null)
                return result;
        }

        return null;
    }

    private User[] recursivelyGetAllUsers(Group group) {
        List<User> users = new ArrayList<>();

        User[] groupUsers = group.getUsersArray();

        if (groupUsers.length > 0)
            Collections.addAll(users, groupUsers);

        for (Group subgroup : group.getSubgroupsArray()) {
            Collections.addAll(users, recursivelyGetAllUsers(subgroup));
        }

        return users.toArray(User[]::new);
    }

    @Override
    public User[] getAllUsers() {
        return recursivelyGetAllUsers(rootGroup);
    }

    // Tweets
    @Override
    public Tweet[] getAllTweets() {
        List<Tweet> tweets = new ArrayList<>();

        User[] allUsers = getAllUsers();
        for (User user : allUsers) {
            Collections.addAll(tweets, user.getFeed().getTweets());
        }

        return tweets.toArray(Tweet[]::new);
    }

    @Override
    public User getLastUpdatedUser() {
        User[] users = getAllUsers();

        if (users.length == 0)
            return null;

        Arrays.sort(users, Comparator.comparing(user ->
            user.getFeed().getLastUpdateTime()
        ));

        return users[users.length - 1];
    }
}

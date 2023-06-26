package com.andrewjunggg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataManager {
    private static DataManager instance;
    private final Group rootGroup = new Group("Root");

    private DataManager() {
        User user1 = new User("user1");
        User user2 = new User("user2");
        User user3 = new User("user3");

        user1.addFollowing(user2);
        user1.addFollowing(user3);

        rootGroup.addUser(user1);
        rootGroup.addUser(user2);
        rootGroup.addUser(user3);
    }

    public static DataManager getInstance() {
        if (instance == null)
            instance = new DataManager();
        
        return instance;
    }

    // Group

    public Group getRootGroup() {
        return rootGroup;
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

    public Group findGroupById(String id) {
        Group rootGroup = getRootGroup();
        return findGroupByIdRecursively(id, rootGroup);
    }

    // User

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

    public User[] getAllUsers() {
        return recursivelyGetAllUsers(rootGroup);
    }
}

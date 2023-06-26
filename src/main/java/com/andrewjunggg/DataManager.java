package com.andrewjunggg;

public class DataManager {
    private static DataManager instance;
    private final Group rootGroup = new Group("Root");

    private DataManager() {
        rootGroup.addUser(new User("test1"));
        rootGroup.addUser(new User("test2"));
        rootGroup.addUser(new User("test3"));

        Group subgroup1 = new Group("sub1");
        subgroup1.addUser(new User("test4"));
        subgroup1.addUser(new User("test5"));
        subgroup1.addUser(new User("test6"));
        rootGroup.addSubgroups(subgroup1);
    }

    public static DataManager getInstance() {
        if (instance == null)
            instance = new DataManager();
        
        return instance;
    }

    public Group getRootGroup() {
        return rootGroup;
    }
}

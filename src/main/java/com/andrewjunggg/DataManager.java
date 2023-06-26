package com.andrewjunggg;

public class DataManager {
    private static DataManager instance;
    private final Group rootGroup = new Group("Root");

    private DataManager() {
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

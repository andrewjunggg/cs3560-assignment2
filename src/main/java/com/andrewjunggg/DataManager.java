package com.andrewjunggg;

public class DataManager {
    private static DataManager instance;

    private DataManager() {
        // TODO
    }

    public static DataManager getInstance() {
        if (instance == null)
            instance = new DataManager();
        
        return instance;
    }
}

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
}

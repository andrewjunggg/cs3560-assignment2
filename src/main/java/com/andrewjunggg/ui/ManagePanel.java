package com.andrewjunggg.ui;

import java.awt.Dimension;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.andrewjunggg.DataManager;
import com.andrewjunggg.Group;
import com.andrewjunggg.Tweet;
import com.andrewjunggg.TweetPositiveVisitor;
import com.andrewjunggg.User;

public class ManagePanel extends JPanel {
    private final DataManager dataManager = DataManager.getInstance();
    private Runnable onRefreshListener;
    private Runnable onUserViewListener;

    public ManagePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(350, 150));
        setBorder(BorderFactory.createTitledBorder("Manage"));
        

        JButton addUserButton = new JButton("Add User");
        addUserButton.addActionListener(actionEvent -> {
            JTextField userIdField = new JTextField();
            JTextField groupIdField = new JTextField();
            Object[] request = {
                    "User ID", userIdField,
                    "Group ID", groupIdField,
            };

            JOptionPane.showMessageDialog(null, request, "Add User", JOptionPane.QUESTION_MESSAGE);

            String userId = userIdField.getText();
            String groupId = groupIdField.getText();

            addUser(userId, groupId);

            if (onRefreshListener != null)
                onRefreshListener.run();
        });
        add(addUserButton);

        JButton addGroupButton = new JButton("Add Group");
        addGroupButton.addActionListener(actionEvent -> {
            JTextField groupIdField = new JTextField();
            JTextField parentGroupIdField = new JTextField();
            Object[] request = {
                    "Group ID", groupIdField,
                    "Parent Group ID", parentGroupIdField,
            };

            JOptionPane.showMessageDialog(null, request, "Add Group", JOptionPane.QUESTION_MESSAGE);

            String groupId = groupIdField.getText();
            String parentGroupId = parentGroupIdField.getText();

            addGroup(groupId, parentGroupId);
            
            if (onRefreshListener != null)
                onRefreshListener.run();
        });
        add(addGroupButton);

        JButton openUserViewButton = new JButton("Open User View");
        openUserViewButton.addActionListener(actionEvent -> {
            if (onUserViewListener != null)
                onUserViewListener.run();
        });
        add(openUserViewButton);

        JButton checkGoodnessButton = new JButton("Show Positive Percentage");
        checkGoodnessButton.addActionListener(actionEvent -> {
            double totalGoodness = 0;
            int tweetCount = 0;

            TweetPositiveVisitor tweetGoodnessVisitor = new TweetPositiveVisitor();

            Tweet[] allTweets = dataManager.getAllTweets();
            for (Tweet tweet : allTweets) {
                boolean good = tweet.accept(tweetGoodnessVisitor);
                if (good)
                    totalGoodness++;

                tweetCount += 1;
            }

            if (tweetCount != 0)
                totalGoodness /= tweetCount;

            JOptionPane.showMessageDialog(null, "Positivity: " + totalGoodness * 100 + "%");
        });
        add(checkGoodnessButton);

        JButton statsButton = new JButton("Show Statistics");
        statsButton.addActionListener(actionEvent -> {
            Group[] allGroups = dataManager.getAllGroups();
            User[] allUsers = dataManager.getAllUsers();
            Tweet[] allTweets = dataManager.getAllTweets();

            JOptionPane.showMessageDialog(null, "Total Group(s): " + allGroups.length + "\nTotal User(s): " + allUsers.length + "\nTotal Tweet(s): " + allTweets.length);
        });
        add(statsButton);

        JButton lastUpdatedUserButton = new JButton("Last updated user");

        lastUpdatedUserButton.addActionListener(actionEvent -> {
            User lastUpdatedUser = dataManager.getLastUpdatedUser();

            if (lastUpdatedUser == null) {
                JOptionPane.showMessageDialog(null, "No users updated.");
            } else {
                JOptionPane.showMessageDialog(null, "Last user updated on " + lastUpdatedUser.getFeed().getLastUpdateTime() + " by " + lastUpdatedUser.getId());
            }
        });

        add(lastUpdatedUserButton);

        JButton validateButton = new JButton("Validate User and Group IDs");

        validateButton.addActionListener(actionEvent -> {
            boolean spaceDetected = false;
            User[] allUsers = dataManager.getAllUsers();
            Group[] allGroups = dataManager.getAllGroups();

            Set<String> uniqueUserIds = new HashSet<>();
            Set<String> uniqueGroupIds = new HashSet<>();

            for (User user : allUsers) {
                uniqueUserIds.add(user.getId());

                if (user.getId().contains(" "))
                    spaceDetected = true;
            }


            for (Group group : allGroups) {
                uniqueGroupIds.add(group.getId());

                if (group.getId().contains(" "))
                    spaceDetected = true;
            }

            if (allUsers.length < 1 || allGroups.length < 1)
                JOptionPane.showMessageDialog(null, "No users or groups created.");
            else if (uniqueUserIds.size() != allUsers.length)
                JOptionPane.showMessageDialog(null, "Validation failed! Duplicate users detected.");
            else if (uniqueGroupIds.size() != allGroups.length)
                JOptionPane.showMessageDialog(null, "Validation failed! Duplicate groups detected.");
            else if (spaceDetected)
                JOptionPane.showMessageDialog(null, "Validation failed! Space(s) detected.");
            else
                JOptionPane.showMessageDialog(null, "Validation successful! No user duplicates and all users doesn't contain spaces.");

        });
        add(validateButton);
    }

    private void addUser(String userId, String groupId) {
        if (userId.isEmpty())
            return;

        User user = new User(userId);
        Group group;

        if (groupId.isEmpty()) {
            group = dataManager.getRootGroup();
        } else {
            group = dataManager.findGroupById(groupId);
        }

        if (group != null) {
            group.addUser(user);
        }
    }

    private void addGroup(String groupId, String parentGroupId) {
        if (groupId.isEmpty())
            return;

        Group group = new Group(groupId);
        Group parentGroup;

        if (parentGroupId.isEmpty()) {
            parentGroup = dataManager.getRootGroup();
        } else {
            parentGroup = dataManager.findGroupById(parentGroupId);
        }

        if (parentGroup != null) {
            parentGroup.addSubgroup(group);
        }
    }

    public void setRefreshListener(Runnable onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
    }

    public void setUserViewListener(Runnable onUserViewListener) {
        this.onUserViewListener = onUserViewListener;
    }
}

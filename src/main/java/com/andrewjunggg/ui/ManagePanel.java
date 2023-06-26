package com.andrewjunggg.ui;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.andrewjunggg.DataManager;
import com.andrewjunggg.Group;
import com.andrewjunggg.User;

public class ManagePanel extends JPanel {
    private final DataManager dataManager = DataManager.getInstance();
    private Runnable onRefreshListener;
    private Runnable onUserViewListener;

    public ManagePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(300, 300));
        setBorder(BorderFactory.createTitledBorder("Manage"));

        JButton addUserButton = new JButton("Add user");
        addUserButton.addActionListener(actionEvent -> {
            JTextField userIdField = new JTextField();
            JTextField groupIdField = new JTextField();
            Object[] request = {
                    "User ID", userIdField,
                    "Group ID", groupIdField,
            };

        JOptionPane.showConfirmDialog(null, request, "Add user", JOptionPane.OK_CANCEL_OPTION);

        addUser(userIdField.getText(), groupIdField.getText());
            
        if (onRefreshListener != null)
            onRefreshListener.run();
        });

        JButton addGroupButton = new JButton("Add group");
        addGroupButton.addActionListener(actionEvent -> {
            JTextField groupIdField = new JTextField();
            JTextField parentGroupIdField = new JTextField();
            // What if no text provided?
            Object[] request = {
                "Group ID", groupIdField,
                "Parent Group ID", parentGroupIdField,
            };

            JOptionPane.showConfirmDialog(null, request, "Add Group", JOptionPane.OK_CANCEL_OPTION);

            addGroup(groupIdField.getText(), parentGroupIdField.getText());

            if (onRefreshListener != null)
                onRefreshListener.run();
        });

        JButton userViewButton = new JButton("Open user view");
        userViewButton.addActionListener(actionEvent -> {
            if (onUserViewListener != null)
                onUserViewListener.run();
        });

        add(addUserButton);
        add(addGroupButton);
        add(userViewButton);
    }

    public void setRefreshListener(Runnable refreshListener) {
        this.onRefreshListener = refreshListener;
    }

    public void setUserViewListener(Runnable userViewListener) {
        this.onUserViewListener = userViewListener;
    }

    private void addUser(String idString, String groupIdString) {
        User user = new User(idString);
        Group group;

        if (groupIdString.isEmpty()) {
            group = dataManager.getRootGroup();
        } else {
            group = dataManager.findGroupById(groupIdString);
        }

        if (group != null) {
            group.addUser(user);
        }
    }

    private void addGroup(String groupIdString, String parentGroupIdString) {
        Group group = new Group(groupIdString);
        Group parentGroup;

        if (parentGroupIdString.isEmpty()) {
            parentGroup = dataManager.getRootGroup();
        } else {
            parentGroup = dataManager.findGroupById(parentGroupIdString);
        }

        if (parentGroup != null) {
            parentGroup.addSubgroup(group);
        }
    }
}

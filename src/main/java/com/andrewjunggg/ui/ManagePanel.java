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

public class ManagePanel extends JPanel {
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

            JOptionPane.showConfirmDialog(null, request);
            System.out.println(userIdField.getText());
            System.out.println(groupIdField.getText());
            // TODO: add new user and refresh
            if (onRefreshListener != null)
                onRefreshListener.run();
        });

        JButton addGroupButton = new JButton("Add group");
        addGroupButton.addActionListener(actionEvent -> {
            String id = JOptionPane.showInputDialog("Group ID");
            DataManager.getInstance().getRootGroup().addSubgroups(new Group(id));
            
            // TODO: add new group and refresh
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
}

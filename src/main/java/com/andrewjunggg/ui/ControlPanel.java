package com.andrewjunggg.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.andrewjunggg.User;
import com.andrewjunggg.UserView;

public class ControlPanel extends JFrame {
    public ControlPanel() {
        setResizable(false);
        setSize(800, 500);

        SideViewPanel sideViewPanel = new SideViewPanel();
        add(sideViewPanel, BorderLayout.WEST);

        ManagePanel managePanel = new ManagePanel();
        managePanel.setRefreshListener(sideViewPanel::update);
        managePanel.setUserViewListener(() -> {
            User selectedUser = sideViewPanel.getSelectedUser();

            if (selectedUser == null)
                return;

            UserView userView = new UserView(selectedUser);
            userView.show();
        });
        add(managePanel, BorderLayout.EAST);
    }

    public void show() {
        setVisible(true);
    }
}
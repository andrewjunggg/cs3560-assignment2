package com.andrewjunggg.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.andrewjunggg.User;
import com.andrewjunggg.UserView;

public class ControlPanel {
    private final JFrame frame;

    public ControlPanel() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setSize(800, 800);

        SideViewPanel sideViewPanel = new SideViewPanel();

        ManagePanel managePanel = new ManagePanel();
        managePanel.setRefreshListener(sideViewPanel::update);
        managePanel.setUserViewListener(() -> {
            User selectedUser = sideViewPanel.getSelectedUser();

            if (selectedUser == null)
                return;

            UserView userView = new UserView(selectedUser);
            userView.show();
        });

        frame.add(sideViewPanel, BorderLayout.WEST);
        frame.add(managePanel, BorderLayout.EAST);
    }

    public void show() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

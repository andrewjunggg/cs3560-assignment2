package com.andrewjunggg.ui;

import java.awt.*;

import javax.swing.JFrame;

import com.andrewjunggg.User;

public class ControlView extends JFrame {
    public ControlView() {
        setResizable(false);
        setSize(1000, 1000);

        SideViewPanel sideViewPanel = new SideViewPanel();
        add(sideViewPanel, BorderLayout.WEST);

        ManagePanel managePanel = new ManagePanel();
        managePanel.setRefreshListener(sideViewPanel::update);
        managePanel.setUserViewListener(() -> {
            User selectedUser = sideViewPanel.getSelectedUser();

            if (selectedUser == null)
                return;

            UserView userView = new UserView(selectedUser);
            userView.present();
        });
        add(managePanel, BorderLayout.EAST);
    }

    public void present() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
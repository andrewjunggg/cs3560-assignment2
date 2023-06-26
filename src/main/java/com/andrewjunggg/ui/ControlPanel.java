package com.andrewjunggg.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.andrewjunggg.UserView;

public class ControlPanel {
    private final JFrame frame;

    public ControlPanel() {
        frame = new JFrame();
        frame.setResizable(false);
        frame.setSize(800, 800);

        SideViewPanel sideViewPanel = new SideViewPanel();

        ManagePanel managePanel = new ManagePanel();
        managePanel.setUserViewListener(() -> {
            // TODO: pass selected user
            UserView user = new UserView(null);
            user.show();
        });

        frame.add(sideViewPanel, BorderLayout.WEST);
        frame.add(managePanel, BorderLayout.EAST);
    }

    public void show() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

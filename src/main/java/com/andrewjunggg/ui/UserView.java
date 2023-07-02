package com.andrewjunggg.ui;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import com.andrewjunggg.User;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class UserView extends JFrame {
    private final User user;

    public UserView(User user) {
        this.user = user;

        setResizable(false);
        setSize(400, 500);
        setTitle(user.getId());

        FeedPanel feedPanel = new FeedPanel(user);
        FollowingPanel followingPanel = new FollowingPanel(user);
        followingPanel.setRefreshListener(feedPanel::update);

        add(followingPanel, BorderLayout.NORTH);
        add(feedPanel);

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent windowEvent) {
            }

            @Override
            public void windowClosing(WindowEvent windowEvent) {
                feedPanel.removeAllWatchers();
            }

            @Override
            public void windowClosed(WindowEvent windowEvent) {
            }

            @Override
            public void windowIconified(WindowEvent windowEvent) {
            }

            @Override
            public void windowDeiconified(WindowEvent windowEvent) {
            }

            @Override
            public void windowActivated(WindowEvent windowEvent) {
            }

            @Override
            public void windowDeactivated(WindowEvent windowEvent) {
            }
        });
    }

    public void present() {
        setVisible(true);
    }

    public User getUser() {
        return user;
    }
}

package com.andrewjunggg;

import javax.swing.JFrame;

public class UserView {
    private final JFrame frame;
    private final User user;

    public UserView(User user) {
        this.user = user;

        frame = new JFrame();
        frame.setResizable(false);
        frame.setSize(800, 800);
        frame.setTitle(user.getId());
    }

    public void show() {
        frame.setVisible(true);
    }

    public User getUser() {
        return user;
    }
}

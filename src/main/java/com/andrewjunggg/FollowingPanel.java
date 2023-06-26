package com.andrewjunggg;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class FollowingPanel extends JPanel {
    private final User user;

    public FollowingPanel(User user) {
        this.user = user;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(500, 500));
        setBorder(BorderFactory.createTitledBorder("Following"));

        JList<String> list = new JList<>(user.getFollowingIds());
        JScrollPane listScroller = new JScrollPane(list);
        add(listScroller);
    }

    public User getUser() {
        return user;
    }
}

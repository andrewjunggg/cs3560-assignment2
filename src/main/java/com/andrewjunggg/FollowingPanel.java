package com.andrewjunggg;

import java.awt.Dimension;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class FollowingPanel extends JPanel {
    private final User user;
    
    private final DataManager dataManager = DataManager.getInstance();

    private Runnable onRefreshListener;

    public FollowingPanel(User user) {
        this.user = user;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(400, 200));
        setBorder(BorderFactory.createTitledBorder("Following"));

        buildPanel();
    }

    private String[] getFollowingIds() {
        User[] following = user.getFollowingArray();
        return Arrays.stream(following).map(User::getId).toArray(String[]::new);
    }

    private void buildPanel() {
        removeAll();

        String[] followingIds = getFollowingIds();
        JList<String> list = new JList<>(followingIds);
        JScrollPane listScroller = new JScrollPane(list);
        add(listScroller);

        JButton addFollowingButton = new JButton("Follow user");
        addFollowingButton.addActionListener(actionEvent -> {
            String userId = JOptionPane.showInputDialog("User ID");

            if (dataManager.findUserById(userId) != null) {
                followUser(userId);
                update();
            } else {
                JOptionPane.showMessageDialog(null, "No user with that ID exists.");
            }
        });
        add(addFollowingButton);
    }

    private void followUser(String userId) {
        User followedUser = dataManager.findUserById(userId);

        if (followedUser == null)
            return;

        user.addFollowing(followedUser);

        followedUser.addFollower(user);
    }

    public void update() {
        buildPanel();
        validate();
        repaint();

        if (onRefreshListener != null)
            onRefreshListener.run();
    }

    public User getUser() {
        return user;
    }

    public void setRefreshListener(Runnable onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
    }
}

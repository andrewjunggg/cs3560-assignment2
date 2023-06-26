package com.andrewjunggg;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class FeedPanel extends JPanel {
    private final User user;
    private final Set<User> followingWatching = new HashSet<>();

    public FeedPanel(User user) {
        this.user = user;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createTitledBorder("Feed"));

        buildPanel();
    }

    public void removeAllWatchers() {
        for (User following : followingWatching) {
            following.getFeed().removeWatcher(this::update);
        }
        followingWatching.clear();
    }

    private void updateWatchers() {
        removeAllWatchers();

        for (User following : user.getFollowingArray()) {
            Feed feed = following.getFeed();
            feed.addWatcher(this::update);
            followingWatching.add(following);
        }

        Feed feed = user.getFeed();
        feed.addWatcher(this::update);
        followingWatching.add(user);
    }

    private String[] getUserFeedContent() {
        Feed feed = new Feed();

        feed.addTweets(user.getFeed().getTweets());

        for (User following : user.getFollowingSet()) {
            feed.addTweets(following.getFeed().getTweets());
        }

        return Arrays.stream(feed.getTweets()).map(Tweet::getFormattedContent).toArray(String[]::new);
    }

    private void buildPanel() {
        removeAll();

        String[] feed = getUserFeedContent();
        updateWatchers();

        JList<String> list = new JList<>(feed);
        JScrollPane listScroller = new JScrollPane(list);
        add(listScroller);

        JButton addTweetButton = new JButton("Add Tweet");
        addTweetButton.addActionListener(actionEvent -> {
            String content = JOptionPane.showInputDialog("Content");
            Tweet tweet = new Tweet(content, user);
            boolean isValidLength = tweet.accept(new TweetLengthValidatorVisitor());

            if (isValidLength)
                user.getFeed().addTweet(tweet);
            else
                JOptionPane.showMessageDialog(null, "Tweet must be less than " + TweetLengthValidatorVisitor.MAX_TWEET_LENGTH + " characters long.");
        });
        add(addTweetButton);
    }

    public void update() {
        buildPanel();
        validate();
        repaint();
    }

    public User getUser() {
        return user;
    }
}
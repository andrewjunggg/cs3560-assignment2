package com.andrewjunggg;

import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;

public class Feed extends Watchable {
    private final TreeSet<Tweet> tweets = new TreeSet<>(Comparator.comparing(Tweet::getDate));

    public Tweet[] getTweets() {
        return tweets.toArray(Tweet[]::new);
    }

    public void setTweets(Tweet[] tweets) {
        this.tweets.clear();
        addTweets(tweets);
    }

    public void addTweet(Tweet tweet) {
        tweets.add(tweet);
        notifyWatchers();
    }

    public void addTweets(Tweet[] tweets) {
        Collections.addAll(this.tweets, tweets);
        notifyWatchers();
    }
}
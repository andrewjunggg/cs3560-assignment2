package com.andrewjunggg;

import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;

public class Feed extends Watchable {
    private final TreeSet<Tweet> tweets = new TreeSet<>(Comparator.comparing(Tweet::getDate));

    private long lastUpdateTime = -1;

    public Tweet[] getTweets() {
        return tweets.toArray(Tweet[]::new);
    }

    public void setTweets(Tweet[] tweets) {
        this.tweets.clear();
        addTweets(tweets);
    }

    public void addTweet(Tweet tweet) {
        tweets.add(tweet);
        lastUpdateTime = System.currentTimeMillis();
        notifyWatchers();
    }

    public void addTweets(Tweet[] tweets) {
        Collections.addAll(this.tweets, tweets);
        lastUpdateTime = System.currentTimeMillis();
        notifyWatchers();
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTimeLong) {
        this.lastUpdateTime = lastUpdateTimeLong;
    }
}
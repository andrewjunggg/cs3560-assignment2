package com.andrewjunggg;

import java.util.Date;

public class Tweet extends ITweet implements TweetVisitable {
    public Tweet(String content, User user) {
        super(content, user);
    }

    public Tweet(String content, User user, Date date) {
        super(content, user, date);
    }

    @Override
    public String getFormattedContent() {
        return "[" + getDate() + "] " + getUser().getId() + ": " + getContent();
    }

    @Override
    public boolean accept(TweetVisitor visitor) {
        return visitor.visit(this);
    }
}
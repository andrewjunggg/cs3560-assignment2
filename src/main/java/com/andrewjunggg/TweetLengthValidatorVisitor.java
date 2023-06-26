package com.andrewjunggg;

public class TweetLengthValidatorVisitor implements TweetVisitor {
    public static final int MAX_TWEET_LENGTH = 100;

    @Override
    public boolean visit(Tweet tweet) {
        String content = tweet.getContent();
        return content.length() <= MAX_TWEET_LENGTH;
    }
}
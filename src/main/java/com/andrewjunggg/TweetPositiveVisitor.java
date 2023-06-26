package com.andrewjunggg;

import java.util.Arrays;

public class TweetPositiveVisitor implements TweetVisitor {
    static final String[] GOOD_WORDS = {"positive"};

    @Override
    public boolean visit(Tweet tweet) {
        String content = tweet.getContent();
        String[] words = content.split(" ");

        if (words.length != 0) {
            for (String word : words) {
                if (Arrays.asList(GOOD_WORDS).contains(word.toLowerCase())) {
                    return true;
                }
            }
        }

        return false;
    }
}
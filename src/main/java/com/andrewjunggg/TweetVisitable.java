package com.andrewjunggg;

public interface TweetVisitable {

    boolean accept(TweetVisitor visitor);

}
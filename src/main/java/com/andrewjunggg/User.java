package com.andrewjunggg;

import java.util.HashSet;
import java.util.Set;

// - add follower
// - remove follower
// - add following
// - remove following

public class User extends Identity {
    
    private Set<User> followers = new HashSet<>();
    private Set<User> followings = new HashSet<>();
    private Tweet[] feed;

    
    public User(String idString) {
        super(idString);
    }

    // getter

    public Set<User> getFollowerSet() {
        return followers;
    }

    public Set<User> getFollowingSet() {
        return followings;
    }

    public User[] getFollowingArray() {
        return followings.toArray(User[]::new);
    }

    public Tweet[] getFeed() {
        return feed;
    }

    // setter

    public void setFeed(Tweet[] feed) {
        this.feed = feed;
    }

    // methods

    public void addFollower(User user) {
        this.followers.add(user);
    }

    public void removeFollower(User user) {
        this.followers.remove(user);
    }

    public void addFollowing(User user) {
        this.followings.add(user);
        user.addFollower(this); // Adds me to the user's follower set
    }

    public void removeFollowing(User user) {
        this.followings.remove(user);
        user.removeFollower(this); // Removes me from the user's follower set
    }
}

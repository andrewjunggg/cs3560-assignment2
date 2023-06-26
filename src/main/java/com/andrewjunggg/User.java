package com.andrewjunggg;

import java.util.HashSet;
import java.util.Set;

// - add follower
// - remove follower
// - add following
// - remove following

public class User extends Identity {
    private final Feed feed = new Feed();
    private Set<User> followers = new HashSet<>();
    private Set<User> followings = new HashSet<>();

    
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

    public Feed getFeed() {
        return feed;
    }

    // setter

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }

    public void setFollowing(Set<User> following) {
        this.followings = following;
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

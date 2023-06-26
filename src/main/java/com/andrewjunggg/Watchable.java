package com.andrewjunggg;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Watchable {
    private final List<Watcher> watchers = new CopyOnWriteArrayList<>();

    public void addWatcher(Watcher watcher) {
        watchers.add(watcher);
    }

    public void removeWatcher(Watcher watcher) {
        watchers.remove(watcher);
    }

    public void notifyWatchers() {
        watchers.forEach(Watcher::update);
    }
}
public DeletionTime search(Composite name) {
        int idx = searchInternal(name);
        return idx < 0 ? null : new DeletionTime(markedAts[idx], delTimes[idx]);
    }
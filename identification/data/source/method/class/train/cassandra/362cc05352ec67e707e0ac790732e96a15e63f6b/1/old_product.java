public DeletionTime search(ByteBuffer name) {
        int idx = searchInternal(name);
        return idx < 0 ? null : new DeletionTime(markedAts[idx], delTimes[idx]);
    }
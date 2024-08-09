@Override
    public int size() {
        return doWithReadLock(c -> c.size());
    }
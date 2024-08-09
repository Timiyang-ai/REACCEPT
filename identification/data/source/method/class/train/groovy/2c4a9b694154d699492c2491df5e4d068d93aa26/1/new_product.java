@Override
    public boolean containsKey(final Object key) {
        return doWithReadLock(c -> c.containsKey(key));
    }
@Override
    public Collection<V> clear() {
        Collection<V> values;

        writeLock.lock();
        try {
            values = map.values();
            map.clear();
        } finally {
            writeLock.unlock();
        }

        return values;
    }
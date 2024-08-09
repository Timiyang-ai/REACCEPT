@Override
    public V get(K key) {
        if (null == key) {
            return null;
        }

        readLock.lock();
        try {
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }
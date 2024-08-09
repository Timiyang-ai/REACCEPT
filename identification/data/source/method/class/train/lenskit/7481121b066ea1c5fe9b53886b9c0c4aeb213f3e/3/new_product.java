public double get(long key) {
        final int idx = keys.getIndexIfActive(key);
        if (idx >= 0) {
            return values[idx];
        } else {
            throw new IllegalArgumentException("Key " + key + " is not in the key set");
        }
    }
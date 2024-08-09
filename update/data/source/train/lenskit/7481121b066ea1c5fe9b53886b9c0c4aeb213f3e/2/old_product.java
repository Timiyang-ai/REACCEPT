public double get(long key) {
        final int idx = findIndex(key);
        if (idx >= 0 && usedKeys.get(idx)) {
            return values[idx];
        } else {
            throw new IllegalArgumentException("Key " + key + " is not in the key set");
        }
    }
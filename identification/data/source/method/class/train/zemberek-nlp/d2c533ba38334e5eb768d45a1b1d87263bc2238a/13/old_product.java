public T getOrAdd(T key) {
        if (key == null)
            throw new IllegalArgumentException("Key cannot be null.");
        if (keyCount + removeCount == threshold) {
            expand();
        }
        int loc = locate(key);
        if (loc >= 0) {
            return keys[loc];
        } else {
            loc = -loc - 1;
            keys[loc] = key;
            keyCount++;
            return key;
        }
    }
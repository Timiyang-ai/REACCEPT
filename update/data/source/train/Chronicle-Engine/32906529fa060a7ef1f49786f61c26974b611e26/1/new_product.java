default boolean remove(K key) {
        return getAndRemove(key) != null;
    }
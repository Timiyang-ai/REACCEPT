public boolean remove(Object key, Object value) {
        int hash = hash(key.hashCode());
        Segment<K,V> s;
        return value != null && (s = segmentForHash(hash)) != null &&
            s.remove(key, hash, value) != null;
    }
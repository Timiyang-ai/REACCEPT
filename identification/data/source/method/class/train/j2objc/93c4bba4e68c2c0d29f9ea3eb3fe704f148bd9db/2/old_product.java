@Override
    public Set<K> keySet() {
        if (keySet == null) {
            keySet = new AbstractSet<K>() {
                @Override
                public boolean contains(Object object) {
                    return containsKey(object);
                }

                @Override
                public int size() {
                    return IdentityHashMap.this.size();
                }

                @Override
                public void clear() {
                    IdentityHashMap.this.clear();
                }

                @Override
                public boolean remove(Object key) {
                    if (containsKey(key)) {
                        IdentityHashMap.this.remove(key);
                        return true;
                    }
                    return false;
                }

                @Override
                public Iterator<K> iterator() {
                    return new IdentityHashMapIterator<K, K, V>(
                            new MapEntry.Type<K, K, V>() {
                                public K get(MapEntry<K, V> entry) {
                                    return entry.key;
                                }
                            }, IdentityHashMap.this);
                }
            };
        }
        return keySet;
    }
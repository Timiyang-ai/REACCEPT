public Set<Map.Entry<K, V>> entrySet() {
        return new Collections.SynchronizedSet<Map.Entry<K, V>>(
                new AbstractSet<Map.Entry<K, V>>() {
                    @Override
                    public int size() {
                        return elementCount;
                    }

                    @Override
                    public void clear() {
                        Hashtable.this.clear();
                    }

                    @Override
                    @SuppressWarnings("unchecked")
                    public boolean remove(Object object) {
                        if (contains(object)) {
                            Hashtable.this.remove(((Map.Entry<K, V>) object)
                                    .getKey());
                            return true;
                        }
                        return false;
                    }

                    @Override
                    @SuppressWarnings("unchecked")
                    public boolean contains(Object object) {
                        Entry<K, V> entry = getEntry(((Map.Entry<K, V>) object)
                                .getKey());
                        return object.equals(entry);
                    }

                    @Override
                    public Iterator<Map.Entry<K, V>> iterator() {
                        return new HashIterator<Map.Entry<K, V>>(
                                new MapEntry.Type<Map.Entry<K, V>, K, V>() {
                                    public Map.Entry<K, V> get(
                                            MapEntry<K, V> entry) {
                                        return entry;
                                    }
                                });
                    }
                }, this);
    }
@Override
    public Collection<V> values() {
        if (valuesCollection == null) {
            valuesCollection = new AbstractCollection<V>() {
                @Override
                public boolean contains(Object object) {
                    return containsValue(object);
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
                public Iterator<V> iterator() {
                    return new IdentityHashMapIterator<V, K, V>(
                            new MapEntry.Type<V, K, V>() {
                                public V get(MapEntry<K, V> entry) {
                                    return entry.value;
                                }
                            }, IdentityHashMap.this);
                }

                @Override
                public boolean remove(Object object) {
                    Iterator<?> it = iterator();
                    while (it.hasNext()) {
                        if (object == it.next()) {
                            it.remove();
                            return true;
                        }
                    }
                    return false;
                }
            };
        }
        return valuesCollection;
    }
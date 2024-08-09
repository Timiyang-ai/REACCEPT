public Collection<V> values() {
        if (valuesCollection == null) {
            valuesCollection = new AbstractCollection<V>() {
                @Override
                public int size() {
                    return AbstractMap.this.size();
                }

                @Override
                public boolean contains(Object object) {
                    return containsValue(object);
                }

                @Override
                public Iterator<V> iterator() {
                    return new Iterator<V>() {
                        Iterator<Map.Entry<K, V>> setIterator = entrySet()
                                .iterator();

                        public boolean hasNext() {
                            return setIterator.hasNext();
                        }

                        public V next() {
                            return setIterator.next().getValue();
                        }

                        public void remove() {
                            setIterator.remove();
                        }
                    };
                }
            };
        }
        return valuesCollection;
    }
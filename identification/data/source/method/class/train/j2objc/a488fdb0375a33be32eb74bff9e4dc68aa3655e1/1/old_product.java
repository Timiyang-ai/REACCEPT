public Set<K> keySet() {
        if (keySet == null) {
            keySet = new AbstractSet<K>() {
                @Override
                public boolean contains(Object object) {
                    return containsKey(object);
                }

                @Override
                public int size() {
                    return AbstractMap.this.size();
                }

                @Override
                public Iterator<K> iterator() {
                    return new Iterator<K>() {
                        Iterator<Map.Entry<K, V>> setIterator = entrySet()
                                .iterator();

                        public boolean hasNext() {
                            return setIterator.hasNext();
                        }

                        public K next() {
                            return setIterator.next().getKey();
                        }

                        public void remove() {
                            setIterator.remove();
                        }
                    };
                }
            };
        }
        return keySet;
    }
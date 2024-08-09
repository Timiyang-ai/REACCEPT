@Override
    public V get(final Object key) {
        final int hash = getHash(key);

        synchronized (locks[hash]) {
            Node<K, V> n = buckets[hash];

            while (n != null) {
                if (n.key == key || (n.key != null && n.key.equals(key))) {
                    return n.value;
                }

                n = n.next;
            }
        }
        return null;
    }
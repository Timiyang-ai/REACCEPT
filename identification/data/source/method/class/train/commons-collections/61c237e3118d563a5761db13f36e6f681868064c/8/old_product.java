public boolean containsKey(final Object key) {
        int hash = getHash(key);

        synchronized (locks[hash]) {
            Node<K, V> n = buckets[hash];

            while (n != null) {
                if (n.key == key || (n.key != null && n.key.equals(key))) {
                    return true;
                }

                n = n.next;
            }
        }
        return false;
    }
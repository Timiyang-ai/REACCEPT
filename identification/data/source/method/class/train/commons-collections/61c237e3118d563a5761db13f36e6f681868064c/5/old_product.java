public Object get(final Object key) {
        int hash = getHash(key);

        synchronized (m_locks[hash]) {
            Node n = m_buckets[hash];

            while (n != null) {
                if (n.key == key || (n.key != null && n.key.equals(key))) {
                    return n.value;
                }

                n = n.next;
            }
        }
        return null;
    }
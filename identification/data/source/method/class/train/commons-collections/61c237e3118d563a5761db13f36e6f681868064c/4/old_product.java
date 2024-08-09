public Object get(final Object key) {
        int hash = getHash(key);

        synchronized (locks[hash]) {
            Node n = buckets[hash];

            while (n != null) {
                if (n.key == key || (n.key != null && n.key.equals(key))) {
                    return n.value;
                }

                n = n.next;
            }
        }
        return null;
    }
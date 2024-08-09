public boolean containsKey(final Object key) {
        int hash = getHash(key);

        synchronized (locks[hash]) {
            Node n = buckets[hash];

            while (n != null) {
                if (n.key == null || (n.key != null && n.key.equals(key))) {
                    return true;
                }

                n = n.next;
            }
        }
        return false;
    }
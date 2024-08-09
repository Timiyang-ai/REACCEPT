public boolean containsValue(final Object value) {
        for (int i = 0; i < buckets.length; i++) {
            synchronized (locks[i]) {
                Node<K, V> n = buckets[i];

                while (n != null) {
                    if (n.value == value || (n.value != null && n.value.equals(value))) {
                        return true;
                    }

                    n = n.next;
                }
            }
        }
        return false;
    }
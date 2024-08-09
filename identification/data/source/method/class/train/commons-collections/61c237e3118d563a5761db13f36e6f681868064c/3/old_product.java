public boolean containsValue(final Object value) {
        for (int i = 0; i < m_buckets.length; i++) {
            synchronized (m_locks[i]) {
                Node n = m_buckets[i];

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
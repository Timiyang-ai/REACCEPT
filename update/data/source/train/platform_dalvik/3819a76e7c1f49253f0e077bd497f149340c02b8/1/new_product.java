@Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof Map) {
            Map<?, ?> map = (Map<?, ?>) object;
            if (size() != map.size()) {
                return false;
            }

            Iterator<Map.Entry<K, V>> it = entrySet().iterator();

            try {
                while (it.hasNext()) {
                    Entry<K, V> entry = it.next();
                    K key = entry.getKey();
                    V value = entry.getValue();
                    Object obj = map.get(key);
                    if( null != obj && (!obj.equals(value)) || null == obj && obj != value) {
                        return false;
                    }
                }
            } catch (ClassCastException cce) {
                return false;
            }
            return true;
        }
        return false;
    }
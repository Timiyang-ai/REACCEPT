@Override
    public void cleanUpNullReferences() {
        List<K> keys = new LinkedList<>();

        for (Map.Entry<K, V> entry : map.entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();
            if (null == value
                    || (value instanceof SoftReference && null == ((SoftReference) value).get())
                    || (value instanceof WeakReference && null == ((WeakReference) value).get())) {
                keys.add(key);
            }
        }

        for (K key : keys) {
            map.remove(key);
        }
    }
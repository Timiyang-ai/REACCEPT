@Override
    public void cleanUpNullReferences() {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            Object entryVal = entry.getValue();
            if (entryVal instanceof SoftReference && ((SoftReference) entryVal).get() == null) {
                map.remove(entry.getKey(), entryVal);
            }
        }
    }
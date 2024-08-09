public void cleanUpNullReferences() {
        for (Map.Entry<Object, Object> entry : cache.entrySet()) {
            Object entryVal = entry.getValue();
            if (entryVal instanceof SoftReference && ((SoftReference) entryVal).get() == null) {
                cache.remove(entry.getKey(), entryVal);
            }
        }
    }
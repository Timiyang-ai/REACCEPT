public void cleanUpNullReferences() {
        final Iterator<Map.Entry<Object, Object>> iterator = cache.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<Object, Object> entry = iterator.next();
            Object entryVal = entry.getValue();
            if (entryVal != null && ((SoftReference) entryVal).get() == null) cache.remove(entry.getKey(), entryVal);
        }
    }
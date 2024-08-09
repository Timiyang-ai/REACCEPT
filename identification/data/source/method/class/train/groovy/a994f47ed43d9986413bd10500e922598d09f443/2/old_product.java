public void cleanUpNullReferences() {
        final Iterator<Map.Entry<Object, Object>> iterator = cache.entrySet().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<Object, Object> entry = iterator.next();
            if (((SoftReference) entry.getValue()).get() == null) cache.remove(entry.getKey(), entry.getValue());
        }
    }
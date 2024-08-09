public Object getSerializable(@NonNull final String key, final Object defaultValue) {
        byte[] bytes = getBytes(key);
        if (bytes == null) return defaultValue;
        return bytes2Object(getBytes(key));
    }
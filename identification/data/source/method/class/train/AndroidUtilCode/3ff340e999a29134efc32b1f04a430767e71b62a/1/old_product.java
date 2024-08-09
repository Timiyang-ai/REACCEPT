public String getString(@NonNull final String key, final String defaultValue) {
        byte[] bytes = getBytes(key);
        if (bytes == null) return defaultValue;
        return bytes2String(bytes);
    }
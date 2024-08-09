public Object getSerializable(@NonNull final String key, final Object defaultValue) {
        byte[] bytes = realGetBytes(TYPE_SERIALIZABLE + key);
        if (bytes == null) return defaultValue;
        return bytes2Object(bytes);
    }
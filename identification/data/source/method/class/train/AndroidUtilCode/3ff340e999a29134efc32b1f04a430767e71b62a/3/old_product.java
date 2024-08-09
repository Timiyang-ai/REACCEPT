public Drawable getDrawable(@NonNull final String key, final Drawable defaultValue) {
        byte[] bytes = getBytes(key);
        if (bytes == null) return defaultValue;
        return bytes2Drawable(bytes);
    }
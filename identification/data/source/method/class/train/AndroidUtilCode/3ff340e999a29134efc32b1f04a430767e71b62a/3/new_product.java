public Drawable getDrawable(@NonNull final String key, final Drawable defaultValue) {
        byte[] bytes = realGetBytes(TYPE_DRAWABLE + key);
        if (bytes == null) return defaultValue;
        return bytes2Drawable(bytes);
    }
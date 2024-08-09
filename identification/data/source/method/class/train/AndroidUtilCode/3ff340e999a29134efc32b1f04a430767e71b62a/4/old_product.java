public Bitmap getBitmap(@NonNull final String key, final Bitmap defaultValue) {
        byte[] bytes = getBytes(key);
        if (bytes == null) return defaultValue;
        return bytes2Bitmap(bytes);
    }
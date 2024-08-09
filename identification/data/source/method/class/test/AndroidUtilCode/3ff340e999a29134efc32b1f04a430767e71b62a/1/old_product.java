public JSONObject getJSONObject(@NonNull final String key, final JSONObject defaultValue) {
        byte[] bytes = getBytes(key);
        if (bytes == null) return defaultValue;
        return bytes2JSONObject(bytes);
    }
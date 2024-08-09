public JSONObject getJSONObject(@NonNull final String key, final JSONObject defaultValue) {
        byte[] bytes = realGetBytes(TYPE_JSON_OBJECT + key);
        if (bytes == null) return defaultValue;
        return bytes2JSONObject(bytes);
    }
public JSONArray getJSONArray(@NonNull final String key, final JSONArray defaultValue) {
        byte[] bytes = realGetBytes(TYPE_JSON_ARRAY + key);
        if (bytes == null) return defaultValue;
        return bytes2JSONArray(bytes);
    }
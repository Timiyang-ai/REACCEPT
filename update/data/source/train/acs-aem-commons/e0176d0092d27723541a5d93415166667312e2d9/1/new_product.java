@SuppressWarnings("unchecked")
    public static <T> Map<String, T> toMap(JSONObject json, Class<T> klass) throws JSONException {
        final HashMap<String, T> map = new HashMap<String, T>();
        final List<?> keys = IteratorUtils.toList(json.keys());

        for (final Object key : keys) {
            final String strKey = key.toString();
            final Object obj = json.get(strKey);
            if(klass.isInstance(obj)) {
                // Only add objects of this type
                map.put(strKey, (T)obj);
            }
        }

        return map;
    }
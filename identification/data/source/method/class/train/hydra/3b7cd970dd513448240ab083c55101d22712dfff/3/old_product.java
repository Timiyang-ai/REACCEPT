public static String getValue(KVPairs kv, String defaultValue, String... keys) {
        String v = getValue(kv, keys);
        return v == null ? defaultValue : v;
    }
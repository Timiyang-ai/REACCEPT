public static String getValue(KVPairs kv, String defaultValue, String... keys) {
        String v = findFirstNonNullValue(kv, keys);
        return v == null ? defaultValue : v;
    }
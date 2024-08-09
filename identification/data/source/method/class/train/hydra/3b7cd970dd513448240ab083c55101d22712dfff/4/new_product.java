public static Long getLongValue(KVPairs kv, Long defaultValue, String... keys) {
        String v = findFirstNonNullValue(kv, keys);
        try {
            return v == null ? defaultValue : Long.parseLong(v);
        } catch (Exception e) {
            return defaultValue;
        }
    }
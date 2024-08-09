public static Long getLongValue(KVPairs kv, Long defaultValue, String... keys) {
        String v = getValue(kv, keys);
        try {
            return v == null ? defaultValue : Long.parseLong(v);
        } catch (Exception e) {
            return null;
        }
    }
public static boolean getBooleanValue(KVPairs kv, boolean defaultValue, String... keys) {
        String v = getValue(kv, keys);
        return v == null ? defaultValue : TRUE_STR_VALUES.contains(v);
    }
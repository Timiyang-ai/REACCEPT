public static boolean getBooleanValue(KVPairs kv, boolean defaultValue, String... keys) {
        String v = findFirstNonEmptyValue(kv, keys);
        return v == null ? defaultValue : TRUE_STR_VALUES.contains(v.toLowerCase());
    }
public static CacheDeserializedValues parseString(String string) {
        String upperCase = StringUtil.upperCaseInternal(string);
        if ("NEVER".equals(upperCase)) {
            return NEVER;
        } else if ("INDEX_ONLY".equals(upperCase) || "INDEX-ONLY".equals(upperCase)) {
            return INDEX_ONLY;
        } else if ("ALWAYS".equals(upperCase)) {
            return ALWAYS;
        } else {
            throw new IllegalArgumentException("Unknown CacheDeserializedValues option '" + string + "'. "
                    + "Possible options: " + Arrays.toString(CacheDeserializedValues.values()));
        }
    }
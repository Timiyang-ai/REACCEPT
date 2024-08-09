public static FastDateFormat getInstance(String pattern) {
        return cache.getInstance(pattern, null, null);
    }
public static FastDateFormat getInstance(String pattern, TimeZone timeZone) {
        return cache.getInstance(pattern, timeZone, null);
    }
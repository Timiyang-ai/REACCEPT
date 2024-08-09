public static FastDateFormat getInstance(String pattern, TimeZone timeZone, Locale locale) {
        return cache.getInstance(pattern, timeZone, locale);
    }
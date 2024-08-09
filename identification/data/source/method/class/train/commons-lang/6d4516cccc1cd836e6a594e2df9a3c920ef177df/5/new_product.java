public static FastDateFormat getInstance(String pattern, Locale locale) {
        return cache.getInstance(pattern, null, locale);
    }
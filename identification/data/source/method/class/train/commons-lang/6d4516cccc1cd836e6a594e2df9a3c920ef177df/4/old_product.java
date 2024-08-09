public static FastDateFormat getInstance(String pattern, TimeZone timeZone, Locale locale) {
        //return getInstance(pattern, timeZone, locale, null);
        Object key = pattern;

        if (timeZone != null) {
            key = new Pair(key, timeZone);
        }
        if (locale != null) {
            key = new Pair(key, locale);
        }

        FastDateFormat format = (FastDateFormat)cInstanceCache.get(key);
        if (format == null) {
            if (locale == null) {
                locale = Locale.getDefault();
            }

            format = new FastDateFormat(pattern, timeZone, locale, new DateFormatSymbols(locale));
            cInstanceCache.put(key, format);
        }
        return format;
    }
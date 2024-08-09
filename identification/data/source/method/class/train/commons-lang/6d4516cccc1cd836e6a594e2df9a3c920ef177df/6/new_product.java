public static synchronized FastDateFormat getInstance(String pattern, TimeZone timeZone, Locale locale) {
        FastDateFormat emptyFormat = new FastDateFormat(pattern, timeZone, locale);
        FastDateFormat format = (FastDateFormat) cInstanceCache.get(emptyFormat);
        if (format == null) {
            format = emptyFormat;
            format.init();  // convert shell format into usable one
            cInstanceCache.put(format, format);  // this is OK!
        }
        return format;
    }
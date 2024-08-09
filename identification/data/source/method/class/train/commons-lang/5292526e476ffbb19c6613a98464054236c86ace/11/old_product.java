public static String format(final Calendar calendar, final String pattern, final TimeZone timeZone, final Locale locale) {
        FastDateFormat df = FastDateFormat.getInstance(pattern, timeZone, locale);
        return df.format(calendar);
    }
public static synchronized Calendar getInstance(TimeZone timezone,
            Locale locale) {
        return new GregorianCalendar(timezone, locale);
    }
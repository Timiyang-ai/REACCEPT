public static synchronized Calendar getInstance(TimeZone timezone, TLocale locale) {
        return new GregorianCalendar(timezone, locale);
    }
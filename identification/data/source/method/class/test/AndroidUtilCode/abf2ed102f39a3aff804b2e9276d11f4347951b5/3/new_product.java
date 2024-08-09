public static String getString(final Date date,
                                   final long timeSpan,
                                   @TimeConstants.Unit final int unit) {
        return getString(date, getDefaultFormat(), timeSpan, unit);
    }
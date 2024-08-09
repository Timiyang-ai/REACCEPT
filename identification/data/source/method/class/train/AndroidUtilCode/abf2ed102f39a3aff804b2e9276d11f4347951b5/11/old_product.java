public static String getString(Date date, long timeSpan, @TimeConstants.Unit int unit) {
        return getString(date, DEFAULT_PATTERN, timeSpan, unit);
    }
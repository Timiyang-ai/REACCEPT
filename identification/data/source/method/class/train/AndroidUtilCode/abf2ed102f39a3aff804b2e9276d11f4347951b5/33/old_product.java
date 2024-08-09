public static String getString(long millis, long timeSpan, @TimeConstants.Unit int unit) {
        return getString(millis, DEFAULT_PATTERN, timeSpan, unit);
    }
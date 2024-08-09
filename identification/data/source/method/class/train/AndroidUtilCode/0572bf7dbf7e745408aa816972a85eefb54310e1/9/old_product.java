public static String getStringByNow(long timeSpan, @TimeConstants.Unit int unit) {
        return getStringByNow(timeSpan, unit, DEFAULT_PATTERN);
    }
public static String getString(long millis, String pattern, long timeSpan, @TimeConstants.Unit int unit) {
        return millis2String(millis + timeSpan2Millis(timeSpan, unit), pattern);
    }
public static String getString(long millis, DateFormat format, long timeSpan, @TimeConstants.Unit int unit) {
        return millis2String(millis + timeSpan2Millis(timeSpan, unit), format);
    }
public static String getString(final long millis, final DateFormat format, final long timeSpan, @TimeConstants.Unit final int unit) {
        return millis2String(millis + timeSpan2Millis(timeSpan, unit), format);
    }
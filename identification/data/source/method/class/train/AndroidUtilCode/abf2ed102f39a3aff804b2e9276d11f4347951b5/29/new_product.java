public static String getString(final String time, final DateFormat format, final long timeSpan, @TimeConstants.Unit final int unit) {
        return millis2String(string2Millis(time, format) + timeSpan2Millis(timeSpan, unit), format);
    }
public static long getMillis(final String time, final long timeSpan, @TimeConstants.Unit final int unit) {
        return getMillis(time, DEFAULT_FORMAT, timeSpan, unit);
    }
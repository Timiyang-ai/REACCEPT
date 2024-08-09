public static long getMillis(final Date date, final long timeSpan, @TimeConstants.Unit final int unit) {
        return date2Millis(date) + timeSpan2Millis(timeSpan, unit);
    }
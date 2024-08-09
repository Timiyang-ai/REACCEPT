public static Date getDate(final long millis, final long timeSpan, @TimeConstants.Unit final int unit) {
        return millis2Date(millis + timeSpan2Millis(timeSpan, unit));
    }
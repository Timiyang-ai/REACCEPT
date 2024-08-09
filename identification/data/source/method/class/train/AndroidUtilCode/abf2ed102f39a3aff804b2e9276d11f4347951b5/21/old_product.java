public static long getMillis(long millis, long timeSpan, @TimeConstants.Unit int unit) {
        return millis + timeSpan2Millis(timeSpan, unit);
    }
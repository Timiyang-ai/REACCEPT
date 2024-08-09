public static long getTimeSpanByNow(final long millis, @TimeConstants.Unit final int unit) {
        return getTimeSpan(millis, System.currentTimeMillis(), unit);
    }
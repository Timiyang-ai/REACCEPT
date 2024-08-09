public static long getTimeSpanByNow(long millis, @TimeConstants.Unit int unit) {
        return getTimeSpan(System.currentTimeMillis(), millis, unit);
    }
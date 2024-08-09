public static long getTimeSpanByNow(long millis, ConstUtils.TimeUnit unit) {
        return getTimeSpan(System.currentTimeMillis(), millis, unit);
    }
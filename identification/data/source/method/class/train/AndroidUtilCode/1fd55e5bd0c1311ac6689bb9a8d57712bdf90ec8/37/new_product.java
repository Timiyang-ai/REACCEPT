public static long getTimeSpanByNow(long millis, @TimeConstant.Unit int unit) {
        return getTimeSpan(System.currentTimeMillis(), millis, unit);
    }
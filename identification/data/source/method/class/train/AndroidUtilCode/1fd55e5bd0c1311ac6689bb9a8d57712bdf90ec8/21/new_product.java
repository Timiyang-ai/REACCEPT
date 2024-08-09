public static long getTimeSpanByNow(String time, ConstUtils.TimeUnit unit) {
        return getTimeSpan(getNowTimeString(), time, unit, DEFAULT_PATTERN);
    }
public static long getTimeSpanByNow(String time, ConstUtils.TimeUnit unit, String pattern) {
        return getTimeSpan(getNowTimeString(), time, unit, pattern);
    }
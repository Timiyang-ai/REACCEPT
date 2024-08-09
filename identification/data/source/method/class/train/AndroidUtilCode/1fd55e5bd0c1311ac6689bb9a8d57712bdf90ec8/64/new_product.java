public static long getTimeSpanByNow(String time, TimeUnit unit, String pattern) {
        return getTimeSpan(getNowTimeString(), time, unit, pattern);
    }
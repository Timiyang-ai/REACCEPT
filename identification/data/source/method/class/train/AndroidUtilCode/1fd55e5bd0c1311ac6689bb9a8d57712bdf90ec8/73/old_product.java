public static long getTimeSpan(long millis0, long millis1, ConstUtils.TimeUnit unit) {
        return ConvertUtils.millis2TimeSpan(Math.abs(millis0 - millis1), unit);
    }
public static long getTimeSpan(String time0, String time1, ConstUtils.TimeUnit unit, String pattern) {
        return ConvertUtils.millis2TimeSpan(Math.abs(string2Millis(time0, pattern) - string2Millis(time1, pattern)), unit);
    }
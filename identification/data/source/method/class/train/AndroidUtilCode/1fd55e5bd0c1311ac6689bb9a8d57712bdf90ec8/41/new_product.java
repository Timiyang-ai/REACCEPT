public static long getTimeSpan(String time0, String time1, @TimeConstant.Unit int unit) {
        return getTimeSpan(time0, time1, unit, DEFAULT_PATTERN);
    }
public static long getTimeSpanByNow(String time, @TimeConstant.Unit int unit) {
        return getTimeSpan(getNowTimeString(), time, unit, DEFAULT_PATTERN);
    }
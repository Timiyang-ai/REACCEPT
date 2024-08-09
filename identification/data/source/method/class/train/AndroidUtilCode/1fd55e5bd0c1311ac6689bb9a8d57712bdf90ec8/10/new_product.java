public static long getTimeSpanByNow(String time, @TimeConstant.Unit int unit, String pattern) {
        return getTimeSpan(getNowTimeString(), time, unit, pattern);
    }
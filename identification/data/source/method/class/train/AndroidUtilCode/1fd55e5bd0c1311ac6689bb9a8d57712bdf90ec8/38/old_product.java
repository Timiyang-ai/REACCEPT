public static long getTimeSpanByNow(String time, @TimeConstants.Unit int unit, String pattern) {
        return getTimeSpan(getNowTimeString(), time, unit, pattern);
    }
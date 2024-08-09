public static long getTimeSpanByNow(String time, @TimeConstants.Unit int unit) {
        return getTimeSpan(getNowTimeString(), time, unit, DEFAULT_PATTERN);
    }
public static long getTimeSpanByNow(String time, @TimeConstants.Unit int unit, String pattern) {
        return getTimeSpan(getNowString(), time, unit, pattern);
    }
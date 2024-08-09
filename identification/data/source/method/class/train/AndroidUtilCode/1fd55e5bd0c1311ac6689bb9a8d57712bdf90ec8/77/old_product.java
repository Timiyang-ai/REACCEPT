public static long getTimeSpanByNow(String time, @TimeConstants.Unit int unit) {
        return getTimeSpan(getNowString(), time, DEFAULT_FORMAT, unit);
    }
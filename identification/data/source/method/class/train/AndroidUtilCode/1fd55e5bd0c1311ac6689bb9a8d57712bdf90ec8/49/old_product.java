public static long getTimeSpanByNow(final String time, @TimeConstants.Unit final int unit) {
        return getTimeSpan(getNowString(), time, DEFAULT_FORMAT, unit);
    }
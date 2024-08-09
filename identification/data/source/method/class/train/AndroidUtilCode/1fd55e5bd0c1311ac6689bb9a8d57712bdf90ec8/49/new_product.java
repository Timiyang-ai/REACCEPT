public static long getTimeSpanByNow(final String time, @TimeConstants.Unit final int unit) {
        return getTimeSpan(time, getNowString(), DEFAULT_FORMAT, unit);
    }
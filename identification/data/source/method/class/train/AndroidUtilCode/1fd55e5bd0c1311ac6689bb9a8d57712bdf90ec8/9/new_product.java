public static long getTimeSpanByNow(final String time, final DateFormat format, @TimeConstants.Unit final int unit) {
        return getTimeSpan(getNowString(format), time, format, unit);
    }
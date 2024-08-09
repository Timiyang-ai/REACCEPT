public static long getTimeSpan(final String time0, final String time1, @TimeConstants.Unit final int unit) {
        return getTimeSpan(time0, time1, DEFAULT_FORMAT, unit);
    }
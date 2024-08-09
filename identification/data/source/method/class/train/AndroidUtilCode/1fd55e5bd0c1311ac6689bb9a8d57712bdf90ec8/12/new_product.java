public static long getTimeSpan(final String time1,
                                   final String time2,
                                   @TimeConstants.Unit final int unit) {
        return getTimeSpan(time1, time2, getDefaultFormat(), unit);
    }
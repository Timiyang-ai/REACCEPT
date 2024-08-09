public static long getTimeSpan(final Date date1,
                                   final Date date2,
                                   @TimeConstants.Unit final int unit) {
        return millis2TimeSpan(date2Millis(date1) - date2Millis(date2), unit);
    }
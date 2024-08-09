public static long getTimeSpan(final String time1,
                                   final String time2,
                                   final DateFormat format,
                                   @TimeConstants.Unit final int unit) {
        return millis2TimeSpan(
                Math.abs(string2Millis(time1, format) - string2Millis(time2, format)), unit
        );
    }
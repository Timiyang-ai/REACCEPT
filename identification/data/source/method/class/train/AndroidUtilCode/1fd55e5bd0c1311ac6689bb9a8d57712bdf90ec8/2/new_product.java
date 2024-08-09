public static long getTimeSpan(final String time0, final String time1, final DateFormat format, @TimeConstants.Unit final int unit) {
        return millis2TimeSpan(Math.abs(string2Millis(time0, format) - string2Millis(time1, format)), unit);
    }
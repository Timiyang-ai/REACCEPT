public static long getTimeSpan(final long millis0,
                                   final long millis1,
                                   @TimeConstants.Unit final int unit) {
        return millis2TimeSpan(Math.abs(millis0 - millis1), unit);
    }
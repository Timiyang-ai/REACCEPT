public static long getTimeSpan(final long millis1,
                                   final long millis2,
                                   @TimeConstants.Unit final int unit) {
        return millis2TimeSpan(Math.abs(millis1 - millis2), unit);
    }
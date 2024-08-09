public static long getTimeSpan(long millis0, long millis1, @TimeConstants.Unit int unit) {
        return millis2TimeSpan(Math.abs(millis0 - millis1), unit);
    }
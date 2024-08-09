public static String getFitTimeSpan(long millis0, long millis1, int precision) {
        return millis2FitTimeSpan(Math.abs(millis0 - millis1), precision);
    }
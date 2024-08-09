public static String getFitTimeSpan(final long millis0,
                                        final long millis1,
                                        final int precision) {
        return millis2FitTimeSpan(Math.abs(millis0 - millis1), precision);
    }
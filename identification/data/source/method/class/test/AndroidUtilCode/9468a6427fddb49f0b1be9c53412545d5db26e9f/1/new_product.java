public static String getFitTimeSpan(final String time1,
                                        final String time2,
                                        @NonNull final DateFormat format,
                                        final int precision) {
        long delta = string2Millis(time1, format) - string2Millis(time2, format);
        return millis2FitTimeSpan(delta, precision);
    }
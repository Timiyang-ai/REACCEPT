public static String getFitTimeSpanByNow(final String time,
                                             @NonNull final DateFormat format,
                                             final int precision) {
        return getFitTimeSpan(time, getNowString(format) , format, precision);
    }
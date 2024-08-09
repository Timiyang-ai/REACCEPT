public static String getFitTimeSpanByNow(final String time, final int precision) {
        return getFitTimeSpan(getNowString(), time, DEFAULT_FORMAT, precision);
    }
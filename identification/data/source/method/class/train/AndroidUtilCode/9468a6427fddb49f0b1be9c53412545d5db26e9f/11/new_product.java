public static String getFitTimeSpanByNow(String time, int precision) {
        return getFitTimeSpan(getNowString(), time, precision, DEFAULT_PATTERN);
    }
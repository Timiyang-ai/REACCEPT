public static String getFitTimeSpanByNow(String time, int precision) {
        return getFitTimeSpan(getNowTimeString(), time, precision, DEFAULT_PATTERN);
    }
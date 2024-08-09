public static String getFitTimeSpanByNow(String time, int precision) {
        return getFitTimeSpan(getNowString(), time, DEFAULT_FORMAT, precision);
    }
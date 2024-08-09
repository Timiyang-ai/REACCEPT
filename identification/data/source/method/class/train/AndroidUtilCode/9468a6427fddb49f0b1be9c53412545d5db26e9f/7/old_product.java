public static String getFitTimeSpanByNow(String time, int precision, String pattern) {
        return getFitTimeSpan(getNowTimeString(), time, precision, pattern);
    }
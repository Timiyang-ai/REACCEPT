public static String getFitTimeSpan(String time0, String time1, int precision) {
        return ConvertUtils.millis2FitTimeSpan(Math.abs(string2Millis(time0, DEFAULT_PATTERN) - string2Millis(time1, DEFAULT_PATTERN)), precision);
    }
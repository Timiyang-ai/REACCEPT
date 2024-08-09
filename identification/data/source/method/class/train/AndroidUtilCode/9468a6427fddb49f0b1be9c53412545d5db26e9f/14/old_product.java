public static String getFitTimeSpan(String time0, String time1, DateFormat format, int precision) {
        return millis2FitTimeSpan(Math.abs(string2Millis(time0, format) - string2Millis(time1, format)), precision);
    }
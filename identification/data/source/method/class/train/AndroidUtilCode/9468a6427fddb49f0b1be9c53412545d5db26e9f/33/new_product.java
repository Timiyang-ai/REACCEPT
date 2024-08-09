public static String getFitTimeSpan(Date date0, Date date1, int precision) {
        return millis2FitTimeSpan(Math.abs(date2Millis(date0) - date2Millis(date1)), precision);
    }
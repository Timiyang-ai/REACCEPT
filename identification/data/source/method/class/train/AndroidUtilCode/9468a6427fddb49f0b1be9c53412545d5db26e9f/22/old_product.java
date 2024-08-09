public static String getFitTimeSpanByNow(String time, DateFormat format, int precision) {
        return getFitTimeSpan(getNowString(format), time, format, precision);
    }
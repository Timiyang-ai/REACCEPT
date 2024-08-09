public static String getFriendlyTimeSpanByNow(String time, DateFormat format) {
        return getFriendlyTimeSpanByNow(string2Millis(time, format));
    }
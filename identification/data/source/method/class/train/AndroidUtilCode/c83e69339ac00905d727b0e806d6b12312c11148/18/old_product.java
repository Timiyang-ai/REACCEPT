public static String getFriendlyTimeSpanByNow(String time, SimpleDateFormat format) {
        return getFriendlyTimeSpanByNow(string2Millis(time, format));
    }
public static String getFriendlyTimeSpanByNow(String time, String pattern) {
        return getFriendlyTimeSpanByNow(string2Millis(time, pattern));
    }
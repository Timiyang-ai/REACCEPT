public static Date string2Date(String time, String pattern) {
        return new Date(string2Millis(time, pattern));
    }
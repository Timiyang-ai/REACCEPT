public static boolean isToday(String time) {
        return isToday(string2Millis(time, DEFAULT_FORMAT));
    }
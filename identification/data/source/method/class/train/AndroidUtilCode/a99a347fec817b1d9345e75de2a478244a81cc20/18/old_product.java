public static boolean isToday(String time, DateFormat format) {
        return isToday(string2Millis(time, format));
    }
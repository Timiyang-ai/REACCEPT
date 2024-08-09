public static boolean isToday(final String time) {
        return isToday(string2Millis(time, getDefaultFormat()));
    }
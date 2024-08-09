public static boolean isToday(final String time, final DateFormat format) {
        return isToday(string2Millis(time, format));
    }
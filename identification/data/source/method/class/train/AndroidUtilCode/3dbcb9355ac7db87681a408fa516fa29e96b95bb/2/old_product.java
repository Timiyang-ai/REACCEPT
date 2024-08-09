public static Date string2Date(String time, SimpleDateFormat format) {
        return new Date(string2Millis(time, format));
    }
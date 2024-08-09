public static Date string2Date(String formatDate, SimpleDateFormat format) {
        return new Date(string2Milliseconds(formatDate, format));
    }
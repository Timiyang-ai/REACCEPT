public static int getWeekOfYear(String time, SimpleDateFormat format) {
        Date date = string2Date(time, format);
        return getWeekOfYear(date);
    }
public static int getWeekOfMonth(String time, SimpleDateFormat format) {
        Date date = string2Date(time, format);
        return getWeekOfMonth(date);
    }
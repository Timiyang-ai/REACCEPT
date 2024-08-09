public static int getWeekIndex(String time, SimpleDateFormat format) {
        Date date = string2Date(time, format);
        return getWeekIndex(date);
    }
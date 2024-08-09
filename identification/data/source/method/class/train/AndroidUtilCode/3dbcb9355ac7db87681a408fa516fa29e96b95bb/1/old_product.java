public static String date2String(Date time, String pattern) {
        return new SimpleDateFormat(pattern, Locale.getDefault()).format(time);
    }
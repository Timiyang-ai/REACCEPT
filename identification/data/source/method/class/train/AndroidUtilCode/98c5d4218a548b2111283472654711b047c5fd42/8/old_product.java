public static String millis2String(long millis, SimpleDateFormat format) {
        return format.format(new Date(millis));
    }
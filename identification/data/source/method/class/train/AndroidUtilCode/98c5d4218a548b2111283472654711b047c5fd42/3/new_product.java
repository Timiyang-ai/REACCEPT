public static String millis2String(long millis, DateFormat format) {
        return format.format(new Date(millis));
    }
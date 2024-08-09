public static String millis2String(final long millis, final DateFormat format) {
        return format.format(new Date(millis));
    }
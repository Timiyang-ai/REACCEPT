public static String millis2String(final long millis, @NonNull final DateFormat format) {
        return format.format(new Date(millis));
    }
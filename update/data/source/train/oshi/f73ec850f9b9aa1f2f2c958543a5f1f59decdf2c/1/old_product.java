public static String formatDate(Date date) {
        return date == null ? "null" : DATE_FORMAT.format(date);
    }
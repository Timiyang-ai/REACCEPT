public static String formatDate(LocalDate date) {
        return date == null ? "null" : date.format(DATE_FORMATTER);
    }
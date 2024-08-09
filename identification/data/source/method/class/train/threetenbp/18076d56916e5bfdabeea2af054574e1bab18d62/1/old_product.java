public static LocalDate parse(String text) {
        ISOChronology.checkNotNull(text, "Text to parse must not be null");
        return DateTimeFormatters.isoLocalDate().parse(text).mergeStrict().toLocalDate();
    }
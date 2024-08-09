public static LocalDate parse(String text) {
        return DateTimeFormatters.isoLocalDate().parse(text, rule());
    }
public static LocalDate from(TemporalAccessor temporal) {
        LocalDate date = temporal.query(Queries.localDate());
        if (date == null) {
            throw new DateTimeException("Unable to obtain LocalDate from TemporalAccessor: " + temporal.getClass());
        }
        return date;
    }
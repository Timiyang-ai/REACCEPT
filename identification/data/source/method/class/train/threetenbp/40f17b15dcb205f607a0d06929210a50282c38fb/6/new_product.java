public static OffsetDate from(TemporalAccessor temporal) {
        if (temporal instanceof OffsetDate) {
            return (OffsetDate) temporal;
        }
        try {
            LocalDate date = LocalDate.from(temporal);
            ZoneOffset offset = ZoneOffset.from(temporal);
            return new OffsetDate(date, offset);
        } catch (DateTimeException ex) {
            throw new DateTimeException("Unable to obtain OffsetDate from TemporalAccessor: " + temporal.getClass(), ex);
        }
    }
public static OffsetDate from(TemporalAccessor temporal) {
        if (temporal instanceof OffsetDate) {
            return (OffsetDate) temporal;
        }
        LocalDate date = LocalDate.from(temporal);
        ZoneOffset offset = ZoneOffset.from(temporal);
        return new OffsetDate(date, offset);
    }
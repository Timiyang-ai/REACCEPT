static ChronoLocalDate<?> from(TemporalAccessor temporal) {
        if (temporal instanceof ChronoLocalDate) {
            return (ChronoLocalDate<?>) temporal;
        }
        Chronology chrono = temporal.query(TemporalQuery.chronology());
        if (chrono == null) {
            throw new DateTimeException("Unable to obtain ChronoLocalDate from TemporalAccessor: " + temporal.getClass());
        }
        return chrono.date(temporal);
    }
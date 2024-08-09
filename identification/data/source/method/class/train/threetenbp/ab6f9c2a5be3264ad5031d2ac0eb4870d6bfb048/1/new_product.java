static <R extends ChronoLocalDate<R>> ChronoLocalDateTimeImpl<R> of(ChronoLocalDate<R> date, LocalTime time) {
        return new ChronoLocalDateTimeImpl<>(date, time);
    }
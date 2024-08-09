static <R extends Chrono<R>> ChronoLocalDateTimeImpl<R> of(ChronoLocalDate<R> date, LocalTime time) {
        return new ChronoLocalDateTimeImpl<>(date, time);
    }
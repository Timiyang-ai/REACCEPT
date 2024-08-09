public ChronoLocalDate<C> now(ZoneId zone) {
        return now(Clock.system(zone));
    }
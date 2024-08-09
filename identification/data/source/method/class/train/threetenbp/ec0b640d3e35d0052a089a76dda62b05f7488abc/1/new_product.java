public ChronoLocalDate<C> dateNow(ZoneId zone) {
        return dateNow(Clock.system(zone));
    }
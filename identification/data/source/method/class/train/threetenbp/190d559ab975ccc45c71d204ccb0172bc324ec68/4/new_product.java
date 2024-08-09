ChronoLocalDate<C> withEra(Era<C> era) {
        return with(ChronoField.ERA, era.getValue());
    }
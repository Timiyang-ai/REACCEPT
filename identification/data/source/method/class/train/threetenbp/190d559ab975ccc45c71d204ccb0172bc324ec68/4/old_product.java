ChronoLocalDate<C> withEra(Era<C> era) {
        return with(LocalDateTimeField.ERA, era.getValue());
    }
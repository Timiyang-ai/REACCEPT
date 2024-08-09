@Override
    default D minus(TemporalAmount amount) {
        return (D) getChronology().ensureChronoLocalDate(Temporal.super.minus(amount));
    }
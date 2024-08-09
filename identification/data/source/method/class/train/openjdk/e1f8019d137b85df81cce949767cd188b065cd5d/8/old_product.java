@Override
    public default D minus(long amountToSubtract, TemporalUnit unit) {
        return (D) getChronology().ensureChronoLocalDate(Temporal.super.minus(amountToSubtract, unit));
    }
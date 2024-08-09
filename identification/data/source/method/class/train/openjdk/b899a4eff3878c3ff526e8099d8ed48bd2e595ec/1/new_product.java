@Override
    public Instant minus(TemporalAmount amount) {
        return (Instant) amount.subtractFrom(this);
    }
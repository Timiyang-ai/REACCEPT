@Override
    public Instant plus(TemporalAmount amount) {
        return (Instant) amount.addTo(this);
    }
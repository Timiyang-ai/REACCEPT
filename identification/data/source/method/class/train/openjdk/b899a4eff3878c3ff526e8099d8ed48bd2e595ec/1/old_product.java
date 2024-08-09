@Override
    public Instant minus(TemporalSubtractor subtractor) {
        return (Instant) subtractor.subtractFrom(this);
    }
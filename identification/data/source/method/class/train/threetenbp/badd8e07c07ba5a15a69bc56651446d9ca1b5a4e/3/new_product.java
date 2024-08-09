@Override
    public Instant minus(TemporalSubtractor adjuster) {
        return (Instant) adjuster.doMinusAdjustment(this);
    }
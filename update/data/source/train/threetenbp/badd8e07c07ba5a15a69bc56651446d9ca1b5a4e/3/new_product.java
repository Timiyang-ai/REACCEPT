@Override
    public Instant plus(TemporalAdder adjuster) {
        return (Instant) adjuster.doPlusAdjustment(this);
    }
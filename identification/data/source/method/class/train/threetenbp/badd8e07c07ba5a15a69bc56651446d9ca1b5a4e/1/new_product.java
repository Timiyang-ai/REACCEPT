@Override
    public Instant with(TemporalAdjuster adjuster) {
        return (Instant) adjuster.doWithAdjustment(this);
    }
@Override
    public Instant with(WithAdjuster adjuster) {
        return (Instant) adjuster.doWithAdjustment(this);
    }
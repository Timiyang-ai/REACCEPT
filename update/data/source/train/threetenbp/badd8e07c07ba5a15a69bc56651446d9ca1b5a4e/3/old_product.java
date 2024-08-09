@Override
    public Instant plus(PlusAdjuster adjuster) {
        return (Instant) adjuster.doPlusAdjustment(this);
    }
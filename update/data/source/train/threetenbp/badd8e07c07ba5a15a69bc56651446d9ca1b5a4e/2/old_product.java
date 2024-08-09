@Override
    public Instant minus(MinusAdjuster adjuster) {
        return (Instant) adjuster.doMinusAdjustment(this);
    }
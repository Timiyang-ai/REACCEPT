public ChronoDate with(WithAdjuster adjuster) {
        return (ChronoDate) adjuster.doAdjustment(this);
    }
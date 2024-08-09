public ChronoDate with(WithAdjuster adjuster) {
        return (ChronoDate) adjuster.doWithAdjustment(this);
    }
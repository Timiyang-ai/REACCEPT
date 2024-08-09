public ChronoDate with(DateTimeAdjuster adjuster) {
        return (ChronoDate) adjuster.makeAdjustmentTo(this);
    }
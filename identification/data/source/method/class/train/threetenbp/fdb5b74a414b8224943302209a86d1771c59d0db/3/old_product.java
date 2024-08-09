public ChronoDate with(DateTimeAdjuster adjuster) {
        return (ChronoDate) adjuster.doAdjustment(this);
    }
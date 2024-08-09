@Override
    public OffsetDate with(TemporalAdjuster adjuster) {
        // optimizations
        if (adjuster instanceof LocalDate) {
            return with((LocalDate) adjuster, offset);
        } else if (adjuster instanceof ZoneOffset) {
            return with(date, (ZoneOffset) adjuster);
        } else if (adjuster instanceof OffsetDate) {
            return (OffsetDate) adjuster;
        }
        return (OffsetDate) adjuster.adjustInto(this);
    }
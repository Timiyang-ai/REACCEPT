public OffsetDate minus(TemporalSubtractor adjuster) {
        return (OffsetDate) adjuster.subtractFrom(this);
    }
@Override
    public OffsetDate minus(TemporalSubtractor subtractor) {
        return (OffsetDate) subtractor.subtractFrom(this);
    }
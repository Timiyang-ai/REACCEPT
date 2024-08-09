@Override
    public long between(Temporal temporal1, Temporal temporal2) {
        return temporal1.periodUntil(temporal2, this);
    }
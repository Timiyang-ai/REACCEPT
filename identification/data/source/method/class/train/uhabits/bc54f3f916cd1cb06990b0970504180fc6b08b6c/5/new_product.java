public final synchronized double getValue(Timestamp timestamp)
    {
        compute(timestamp, timestamp);
        Score s = getComputedByTimestamp(timestamp);
        if (s == null) throw new IllegalStateException();
        return s.getValue();
    }
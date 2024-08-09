public final synchronized int getValue(long timestamp)
    {
        compute(timestamp, timestamp);
        Score s = getComputedByTimestamp(timestamp);
        if(s == null) throw new IllegalStateException();
        return s.getValue();
    }
public final int getValue(long timestamp)
    {
        Score s = getByTimestamp(timestamp);
        if (s != null) return s.getValue();
        return 0;
    }
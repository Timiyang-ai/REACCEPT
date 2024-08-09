    @Override
    protected LongTimeSeries getTestObject() {
        final LongTimeSeries timeSeries = new LongTimeSeries(TimeBucket.SECOND);
        timeSeries.put(Instant.ofEpochMilli(1000L), 10L);
        timeSeries.put(Instant.ofEpochMilli(100000L), 1000L);
        return timeSeries;
    }
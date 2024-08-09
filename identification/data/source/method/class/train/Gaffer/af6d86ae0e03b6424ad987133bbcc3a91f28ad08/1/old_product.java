@Override
    public Long get(final Instant instant) {
        return timeSeries.get(toLong(timeBucket, instant.toEpochMilli()));
    }
private void addMetric(Stream.Builder<String> metrics, Meter.Id id, @Nullable String suffix, long wallTime, double value) {
        if (value != Double.NaN) {
            metrics.add(writeMetric(id, suffix, wallTime, value));
        }
    }
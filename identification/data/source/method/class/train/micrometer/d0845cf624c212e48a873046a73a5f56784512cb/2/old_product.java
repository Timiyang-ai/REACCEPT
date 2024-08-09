void addMetric(Stream.Builder<String> metrics, Meter.Id id, @Nullable String suffix, long wallTime, double value) {
        if (!Double.isNaN(value)) {
            metrics.add(writeMetric(id, suffix, wallTime, value));
        }
    }
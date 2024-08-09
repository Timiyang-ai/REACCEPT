Stream<String> writeGauge(Gauge gauge) {
        Double value = gauge.value();
        if (Double.isFinite(value)) {
            return Stream.of(writeMetric(gauge.getId(), config().clock().wallTime(), value));
        }
        return Stream.empty();
    }
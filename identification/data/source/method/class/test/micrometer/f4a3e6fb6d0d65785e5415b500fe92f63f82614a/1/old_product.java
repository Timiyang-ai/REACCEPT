Stream<String> writeGauge(Gauge gauge) {
        Double value = gauge.value();
        return !Double.isFinite(value) ? Stream.empty() : Stream.of(writeMetric(gauge.getId(), config().clock().wallTime(), value));
    }
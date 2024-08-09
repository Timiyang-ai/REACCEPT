Stream<String> writeTimeGauge(TimeGauge timeGauge) {
        Double value = timeGauge.value(getBaseTimeUnit());
        return !Double.isFinite(value) ? Stream.empty() : Stream.of(writeMetric(timeGauge.getId(), config().clock().wallTime(), value));
    }
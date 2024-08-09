Stream<String> writeTimeGauge(TimeGauge timeGauge) {
        Double value = timeGauge.value(getBaseTimeUnit());
        if (Double.isFinite(value)) {
            return Stream.of(writeMetric(timeGauge.getId(), config().clock().wallTime(), value));
        }
        return Stream.empty();
    }
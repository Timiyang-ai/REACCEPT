Optional<String> writeTimeGauge(TimeGauge timeGauge) {
        double value = timeGauge.value(getBaseTimeUnit());
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            return Optional.empty();
        }
        return Optional.of(write(timeGauge.getId(), "timeGauge", Fields.Value.tag(), decimal(value)));
    }
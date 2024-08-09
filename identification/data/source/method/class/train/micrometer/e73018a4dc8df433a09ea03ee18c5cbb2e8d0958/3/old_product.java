private Optional<String> writeTimeGauge(TimeGauge timeGauge) {
        return Optional.of(write(timeGauge.getId(), "timeGauge", Fields.Value.tag(), decimal(timeGauge.value(getBaseTimeUnit()))));
    }
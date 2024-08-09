private Optional<String> writeGauge(Gauge gauge) {
        return Optional.of(write(gauge.getId(), "gauge", Fields.Value.tag(), decimal(gauge.value())));
    }
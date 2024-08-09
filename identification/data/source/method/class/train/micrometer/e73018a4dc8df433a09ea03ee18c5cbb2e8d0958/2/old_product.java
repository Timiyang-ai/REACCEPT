Optional<String> writeGauge(Gauge gauge) {
        double value = gauge.value();
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            return Optional.empty();
        }
        return Optional.of(write(gauge.getId(), "gauge", Fields.Value.tag(), decimal(value)));
    }
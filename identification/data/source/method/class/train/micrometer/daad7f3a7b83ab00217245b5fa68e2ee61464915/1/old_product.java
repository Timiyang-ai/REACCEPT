private Stream<String> writeGauge(Gauge gauge) {
        Double value = gauge.value();
        return value.isNaN() ? Stream.empty() : Stream.of(event(gauge.getId(), new Attribute("value", value)));
    }
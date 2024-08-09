private Stream<String> writeGauge(TimeGauge gauge) {
        Double value = gauge.value(getBaseTimeUnit());
        return value.isNaN() ? Stream.empty() : Stream.of(event(gauge.getId(), new Attribute("value", value)));
    }
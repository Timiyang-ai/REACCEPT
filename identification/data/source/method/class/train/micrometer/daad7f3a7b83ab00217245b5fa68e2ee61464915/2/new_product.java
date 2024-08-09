Stream<String> writeGauge(TimeGauge gauge) {
        Double value = gauge.value(getBaseTimeUnit());
        if (Double.isFinite(value)) {
            return Stream.of(event(gauge.getId(), new Attribute("value", value)));
        }
        return Stream.empty();
    }
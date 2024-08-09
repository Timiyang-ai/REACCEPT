Stream<String> writeGauge(Gauge gauge, long wallTime) {
        Double value = gauge.value();
        return value.isNaN() ? Stream.empty() : Stream.of(index(gauge, wallTime).field("value", value).build());
    }
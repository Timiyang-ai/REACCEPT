private Stream<String> writeGauge(Gauge gauge, long wallTime) {
        return Stream.of(index(gauge, wallTime).field("count", gauge.value()).build());
    }
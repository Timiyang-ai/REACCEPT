private Stream<String> writeGauge(TimeGauge gauge, long wallTime) {
        return Stream.of(index(gauge, wallTime).field("count", gauge.value(getBaseTimeUnit())).build());
    }
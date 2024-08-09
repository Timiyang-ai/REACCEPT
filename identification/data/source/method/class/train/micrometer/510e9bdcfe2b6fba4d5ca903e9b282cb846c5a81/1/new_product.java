Stream<String> writeGauge(Meter.Id id, Double value) {
        return value.isNaN() || value.isInfinite() ? Stream.empty() :
                Stream.of(influxLineProtocol(id, "gauge", Stream.of(new Field("value", value))));
    }
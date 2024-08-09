Stream<String> writeGauge(Meter.Id id, Double value) {
        return !Double.isFinite(value) ? Stream.empty() :
                Stream.of(influxLineProtocol(id, "gauge", Stream.of(new Field("value", value))));
    }
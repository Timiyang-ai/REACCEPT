@Nullable
    Optional<String> writeGauge(Gauge gauge) {
        Double value = gauge.value();
        if (!value.isNaN()) {
            return Optional.of(writeDocument(gauge, builder -> {
                builder.append(",\"value\":").append(value);
            }));
        }
        return Optional.empty();
    }
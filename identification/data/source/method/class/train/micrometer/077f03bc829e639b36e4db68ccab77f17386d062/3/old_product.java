@Nullable
    Optional<String> writeGauge(Gauge gauge) {
        Double value = gauge.value();
        if (!value.isNaN()) {
            return Optional.of(INDEX_LINE + writeDocument(gauge, builder -> {
                builder.append(",\"value\":").append(value);
            }));
        }
        return Optional.empty();
    }
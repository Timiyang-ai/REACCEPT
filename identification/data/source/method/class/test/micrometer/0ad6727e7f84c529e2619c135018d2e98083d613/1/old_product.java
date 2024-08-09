@Nullable
    Optional<String> writeTimeGauge(TimeGauge gauge) {
        Double value = gauge.value();
        if (!value.isNaN()) {
            return Optional.of(writeDocument(gauge, builder -> {
                builder.append(",\"value\":").append(gauge.value(getBaseTimeUnit()));
            }));
        }
        return Optional.empty();
    }
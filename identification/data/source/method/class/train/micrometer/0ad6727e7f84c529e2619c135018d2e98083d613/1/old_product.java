Optional<String> writeTimeGauge(TimeGauge gauge) {
        Double value = gauge.value();
        if (Double.isFinite(value)) {
            return Optional.of(writeDocument(gauge, builder -> {
                builder.append(",\"value\":").append(gauge.value(getBaseTimeUnit()));
            }));
        }
        return Optional.empty();
    }
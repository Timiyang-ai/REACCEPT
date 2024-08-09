@Nullable
        String writeTimeGauge(TimeGauge gauge) {
            Double value = gauge.value();
            if (!value.isNaN()) {
                return writeEvent(gauge, event("value", gauge.value(getBaseTimeUnit())));
            }
            return null;
        }
@Nullable
        String writeTimeGauge(TimeGauge gauge) {
            double value = gauge.value(getBaseTimeUnit());
            if (Double.isFinite(value)) {
                return writeEvent(gauge, event("value", value));
            }
            return null;
        }
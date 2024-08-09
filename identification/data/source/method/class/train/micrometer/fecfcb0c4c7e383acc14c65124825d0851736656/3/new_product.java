@Nullable
        String writeGauge(Gauge gauge) {
            double value = gauge.value();
            if (Double.isFinite(value)) {
                return writeEvent(gauge, event("value", value));
            }
            return null;
        }
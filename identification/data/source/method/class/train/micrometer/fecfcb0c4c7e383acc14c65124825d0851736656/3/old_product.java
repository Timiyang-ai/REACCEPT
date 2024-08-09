@Nullable
        String writeGauge(Gauge gauge) {
            Double value = gauge.value();
            if (!value.isNaN()) {
                return writeEvent(gauge, event("value", gauge.value()));
            }
            return null;
        }
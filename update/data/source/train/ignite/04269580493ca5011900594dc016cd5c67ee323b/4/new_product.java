public void register(String name, BooleanSupplier supplier, @Nullable String desc) {
        addMetric(name, new BooleanGauge(metricName(grpName, name), desc, nonThrowableSupplier(supplier, log)));
    }
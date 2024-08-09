public void register(String name, LongSupplier supplier, @Nullable String desc) {
        addMetric(name, new LongGauge(metricName(grpName, name), desc, nonThrowableSupplier(supplier, log)));
    }
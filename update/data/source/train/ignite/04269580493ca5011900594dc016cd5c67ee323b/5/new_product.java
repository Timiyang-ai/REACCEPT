public void register(String name, DoubleSupplier supplier, @Nullable String desc) {
        addMetric(name, new DoubleGauge(metricName(grpName, name), desc, nonThrowableSupplier(supplier, log)));
    }
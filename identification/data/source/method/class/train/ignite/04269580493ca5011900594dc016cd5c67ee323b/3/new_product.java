public <T> void register(String name, Supplier<T> supplier, Class<T> type, @Nullable String desc) {
        addMetric(name, new ObjectGauge<>(metricName(grpName, name), desc,
            nonThrowableSupplier(supplier, log), type));
    }
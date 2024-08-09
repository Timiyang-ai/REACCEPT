static void registerMethod(MetricsRegistry metricsRegistry, Object osBean, String methodName, String name) {
        registerMethod(metricsRegistry, osBean, methodName, name, 1);
    }
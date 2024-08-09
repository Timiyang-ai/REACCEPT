static void registerMethod(MetricsRegistry metricsRegistry, Object osBean, String methodName, String name) {
        final Method method = getMethod(osBean, methodName);

        if (method == null) {
            return;
        }

        if (long.class.equals(method.getReturnType())) {
            metricsRegistry.register(osBean, name, MANDATORY,
                    new LongProbeFunction() {
                        @Override
                        public long get(Object bean) throws Exception {
                            return (Long) method.invoke(bean);
                        }
                    });
        } else {
            metricsRegistry.register(osBean, name, MANDATORY,
                    new DoubleProbeFunction() {
                        @Override
                        public double get(Object bean) throws Exception {
                            return (Double) method.invoke(bean);
                        }
                    });
        }
    }
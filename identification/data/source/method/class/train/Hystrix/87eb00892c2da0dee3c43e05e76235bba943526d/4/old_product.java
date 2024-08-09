public static void reset() {
        getInstance().notifier.set(null);
        getInstance().concurrencyStrategy.set(null);
        getInstance().metricsPublisher.set(null);
        getInstance().propertiesFactory.set(null);
        getInstance().commandExecutionHook.set(null);
    }
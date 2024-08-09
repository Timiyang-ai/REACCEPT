public static void reset() {
        SINGLETON = new HystrixMetricsPublisherFactory();
        SINGLETON.commandPublishers.clear();
        SINGLETON.threadPoolPublishers.clear();
    }
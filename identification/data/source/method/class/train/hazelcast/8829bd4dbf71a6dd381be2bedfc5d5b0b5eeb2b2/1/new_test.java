    private void readMetrics(MetricsService metricsService, long sequence, MetricConsumer metricConsumer)
            throws InterruptedException, java.util.concurrent.ExecutionException {
        CompletableFuture<RingbufferSlice<Map.Entry<Long, byte[]>>> future = metricsService.readMetrics(sequence);
        RingbufferSlice<Map.Entry<Long, byte[]>> ringbufferSlice = future.get();

        MetricsResultSet metricsResultSet = new MetricsResultSet(ringbufferSlice.nextSequence(), ringbufferSlice.elements());
        metricsResultSet.collections().forEach(entry -> extractMetrics(entry.getValue(), metricConsumer));
    }
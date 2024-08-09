private Map<String, Long> getMetrics() {
    MetricRegistry metricRegistry = MetricsSystem.METRIC_REGISTRY;

    // Get all counters.
    Map<String, Counter> counters = metricRegistry.getCounters();
    // Only the gauge for pinned files is retrieved here, other gauges are statistics of
    // free/used
    // spaces, those statistics can be gotten via other REST apis.
    String filesPinnedProperty =
        MetricsSystem.getMasterMetricName(FileSystemMaster.Metrics.FILES_PINNED);
    @SuppressWarnings("unchecked") Gauge<Integer> filesPinned =
        (Gauge<Integer>) MetricsSystem.METRIC_REGISTRY.getGauges().get(filesPinnedProperty);

    // Get values of the counters and gauges and put them into a metrics map.
    SortedMap<String, Long> metrics = new TreeMap<>();
    for (Map.Entry<String, Counter> counter : counters.entrySet()) {
      metrics.put(counter.getKey(), counter.getValue().getCount());
    }
    metrics.put(filesPinnedProperty, filesPinned.getValue().longValue());
    return metrics;
  }
private void persistMetrics(Deque<MetricValues> metricValues) throws Exception {
    long now = System.currentTimeMillis();
    long lastMetricTime = metricValues.peekLast().getTimestamp();
    long delay = now - TimeUnit.SECONDS.toMillis(lastMetricTime);
    metricValues.add(
      new MetricValues(metricsContextMap, TimeUnit.MILLISECONDS.toSeconds(now),
                       ImmutableList.of(
                         new MetricValue("metrics.process.count", MetricType.COUNTER, metricValues.size()),
                         new MetricValue("metrics.process.delay.ms", MetricType.GAUGE, delay))));
    metricStore.add(metricValues);
    metricsProcessedCount += metricValues.size();
    PROGRESS_LOG.debug("{} metrics metrics persisted. Last metric metric's timestamp: {}. " +
                         "Metrics process delay: {}ms", metricsProcessedCount, lastMetricTime, delay);
  }
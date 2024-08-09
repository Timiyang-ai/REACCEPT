private void persistMetrics(Deque<MetricValues> metricValues) throws Exception {
    long now = System.currentTimeMillis();
    long lastMetricTime = metricValues.peekLast().getTimestamp();
    long delay = now - TimeUnit.SECONDS.toMillis(lastMetricTime);
    metricValues.add(
      new MetricValues(metricsContextMap, TimeUnit.MILLISECONDS.toSeconds(now),
                       ImmutableList.of(
                         new MetricValue(processMetricName, MetricType.COUNTER, metricValues.size()),
                         new MetricValue(delayMetricName, MetricType.GAUGE, delay))));
    metricStore.add(metricValues);
    metricsProcessedCount += metricValues.size();
    PROGRESS_LOG.debug("{} metrics persisted. Last metric's timestamp: {}. " +
                         "Metrics process delay: {}ms", metricsProcessedCount, lastMetricTime, delay);
  }
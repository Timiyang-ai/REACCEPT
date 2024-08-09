private void persistMetrics(Deque<MetricValues> metricValues,
                              Map<TopicIdMetaKey, TopicProcessMeta> topicMetrics) throws Exception {
    long now = System.currentTimeMillis();
    long lastMetricTime = metricValues.peekLast().getTimestamp();
    List<MetricValue> topicLevelDelays = new ArrayList<>();

    //add topic level delay metrics
    for (Map.Entry<TopicIdMetaKey, TopicProcessMeta> entry : topicMetrics.entrySet()) {
      TopicProcessMeta topicProcessMeta = entry.getValue();
      long delay = now - TimeUnit.SECONDS.toMillis(topicProcessMeta.getOldestMetricsTimestamp());
      topicLevelDelays.add(new MetricValue(topicProcessMeta.getOldestMetricsTimestampMetricName(),
                                           MetricType.GAUGE, delay));
      delay = now - TimeUnit.SECONDS.toMillis(topicProcessMeta.getLatestMetricsTimestamp());
      topicLevelDelays.add(new MetricValue(topicProcessMeta.getLatestMetricsTimestampMetricName(),
                                           MetricType.GAUGE, delay));
    }
    List<MetricValue> processorMetrics = new ArrayList<>(topicLevelDelays);
    processorMetrics.add(new MetricValue(processMetricName, MetricType.COUNTER, metricValues.size()));

    metricValues.add(new MetricValues(metricsContextMap, TimeUnit.MILLISECONDS.toSeconds(now), processorMetrics));
    metricStore.add(metricValues);
    metricsProcessedCount += metricValues.size();
    PROGRESS_LOG.debug("{} metrics persisted. Last metric's timestamp: {}",
                       metricsProcessedCount, lastMetricTime);
  }
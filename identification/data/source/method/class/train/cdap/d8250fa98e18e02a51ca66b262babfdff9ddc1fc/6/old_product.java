private void persistMetrics(Deque<MetricValues> metricValues,
                              Map<TopicIdMetaKey, MetricsProcessorStats> topicMetrics) throws Exception {
    long now = System.currentTimeMillis();
    long lastMetricTime = metricValues.peekLast().getTimestamp();
    List<MetricValue> topicLevelDelays = new ArrayList<>();

    //add topic level delay metrics
    for (Map.Entry<TopicIdMetaKey, MetricsProcessorStats> entry : topicMetrics.entrySet()) {
      if (entry.getValue().getOldestMetricsTimestamp() != null) {
        long delay = now - TimeUnit.SECONDS.toMillis(entry.getValue().getOldestMetricsTimestamp());
        topicLevelDelays.add(
          new MetricValue(topicToOldestTimestampDelayMetrics.get(entry.getKey().getTopicId().getTopic()),
                          MetricType.GAUGE, delay));
      }

      if (entry.getValue().getLatestMetricsTimestamp() != null) {
        long delay = now - TimeUnit.SECONDS.toMillis(entry.getValue().getLatestMetricsTimestamp());
        topicLevelDelays.add(
          new MetricValue(topicToLatestTimestampDelayMetrics.get(entry.getKey().getTopicId().getTopic()),
                          MetricType.GAUGE, delay));
      }
    }
    List<MetricValue> processorMetrics = new ArrayList<>(topicLevelDelays);
    processorMetrics.add(new MetricValue(processMetricName, MetricType.COUNTER, metricValues.size()));

    metricValues.add(new MetricValues(metricsContextMap, TimeUnit.MILLISECONDS.toSeconds(now), processorMetrics));
    metricStore.add(metricValues);
    metricsProcessedCount += metricValues.size();
    PROGRESS_LOG.debug("{} metrics persisted. Last metric's timestamp: {}",
                       metricsProcessedCount, lastMetricTime);
  }
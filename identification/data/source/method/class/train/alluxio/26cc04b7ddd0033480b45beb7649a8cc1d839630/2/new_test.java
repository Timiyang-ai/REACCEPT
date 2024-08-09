  @Test
  public void putWorkerMetrics() {
    List<Metric> metrics1 = Lists.newArrayList(
        Metric.from("worker.192_1_1_1.metric1", 10, MetricType.GAUGE),
        Metric.from("worker.192_1_1_1.metric2", 20, MetricType.GAUGE));
    mMetricStore.putWorkerMetrics("192_1_1_1", metrics1);
    List<Metric> metrics2 = Lists.newArrayList(
        Metric.from("worker.192_1_1_2.metric1", 1, MetricType.GAUGE));
    mMetricStore.putWorkerMetrics("192_1_1_2", metrics2);
    assertEquals(Sets.newHashSet(
            Metric.from("worker.192_1_1_1.metric1", 10, MetricType.GAUGE),
            Metric.from("worker.192_1_1_2.metric1", 1, MetricType.GAUGE)),
        mMetricStore.getMetricsByInstanceTypeAndName(MetricsSystem.InstanceType.WORKER, "metric1"));
  }
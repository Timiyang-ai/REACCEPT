  @Test
  public void putClientMetrics() {
    List<Metric> metrics1 = Lists.newArrayList(
        Metric.from("client.192_1_1_1:A.metric1", 10, MetricType.GAUGE),
        Metric.from("client.192_1_1_1:A.metric2", 20, MetricType.GAUGE));
    mMetricStore.putClientMetrics("192_1_1_1", "A", metrics1);
    List<Metric> metrics2 = Lists.newArrayList(
        Metric.from("client.192_1_1_2:C.metric1", 1, MetricType.GAUGE));
    mMetricStore.putClientMetrics("192_1_1_2", "C", metrics2);
    List<Metric> metrics3 = Lists.newArrayList(
        Metric.from("client.192_1_1_1:B.metric1", 15, MetricType.GAUGE),
        Metric.from("client.192_1_1_1:B.metric2", 25, MetricType.GAUGE));
    mMetricStore.putClientMetrics("192_1_1_1", "B", metrics3);
    assertEquals(Sets.newHashSet(
        Metric.from("client.192_1_1_1:A.metric1", 10, MetricType.GAUGE),
        Metric.from("client.192_1_1_2:C.metric1", 1, MetricType.GAUGE),
        Metric.from("client.192_1_1_1:B.metric1", 15, MetricType.GAUGE)),
        mMetricStore.getMetricsByInstanceTypeAndName(MetricsSystem.InstanceType.CLIENT, "metric1"));
  }
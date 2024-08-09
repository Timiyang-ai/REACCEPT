  @Test
  public void getMetrics() {
    final int FILES_PINNED_TEST_VALUE = 100;
    String filesPinnedProperty =
        MetricsSystem.getMetricName(MasterMetrics.FILES_PINNED);
    Gauge<Integer> filesPinnedGauge = () -> FILES_PINNED_TEST_VALUE;
    MetricSet mockMetricsSet = mock(MetricSet.class);
    Map<String, Metric> map = new HashMap<>();
    map.put(filesPinnedProperty, filesPinnedGauge);

    when(mockMetricsSet.getMetrics()).thenReturn(map);
    MetricsSystem.METRIC_REGISTRY.registerAll(mockMetricsSet);

    Response response = mHandler.getMetrics();
    try {
      assertNotNull("Response must be not null!", response);
      assertNotNull("Response must have a entry!", response.getEntity());
      assertTrue("Entry must be a SortedMap!", (response.getEntity() instanceof SortedMap));
      SortedMap<String, Long> metricsMap = (SortedMap<String, Long>) response.getEntity();
      assertFalse("Metrics Map must be not empty!", (metricsMap.isEmpty()));
      assertTrue("Map must contain key " + filesPinnedProperty + "!",
          metricsMap.containsKey(filesPinnedProperty));
      assertEquals(FILES_PINNED_TEST_VALUE, metricsMap.get(filesPinnedProperty).longValue());
    } finally {
      response.close();
    }
  }
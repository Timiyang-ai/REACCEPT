@Test
    public void onNodeMetrics() {
        MetricsService srvc = new MetricsService(getMockContext(), mgr);

        MetricResponse metricRes = getMetricResponse();
        srvc.onNodeMetrics(UUID.randomUUID(), metricRes, SYSTEM_POOL);

        ArgumentCaptor<StompHeaders> destCaptor = ArgumentCaptor.forClass(StompHeaders.class);
        ArgumentCaptor<Object> payloadCaptor = ArgumentCaptor.forClass(Object.class);
        verify(mgr, times(1)).send(destCaptor.capture(), payloadCaptor.capture());

        Assert.assertEquals(StompDestinationsUtils.buildMetricsDest(metricRes.clusterId()), destCaptor.getValue().getDestination());
        Assert.assertArrayEquals(new byte[]{1, 2, 3, 4}, (byte[]) payloadCaptor.getValue());
    }
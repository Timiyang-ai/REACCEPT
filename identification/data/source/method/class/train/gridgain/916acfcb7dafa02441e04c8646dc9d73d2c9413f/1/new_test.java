@Test
    public void onNodeMetrics() {
        MetricsService srvc = new MetricsService(getMockContext(), mgr);

        MetricResponse metricRes = getMetricResponse();
        srvc.onNodeMetrics(UUID.randomUUID(), metricRes, SYSTEM_POOL);

        ArgumentCaptor<String> destCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<byte[]> payloadCaptor = ArgumentCaptor.forClass(byte[].class);
        verify(mgr, times(1)).send(destCaptor.capture(), payloadCaptor.capture());

        Assert.assertEquals(StompDestinationsUtils.buildMetricsDest(metricRes.clusterId()), destCaptor.getValue());
        Assert.assertArrayEquals(new byte[]{1, 2, 3, 4}, payloadCaptor.getValue());
    }
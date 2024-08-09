@Test
    public void onNodeMetrics() {
        MetricsService srvc = new MetricsService(getMockContext(), getMockWebSocketManager());

        MetricResponse metricResponse = getMetricResponse();
        srvc.onNodeMetrics(UUID.randomUUID(), metricResponse, SYSTEM_POOL);

        ArgumentCaptor<StompHeaders> destCaptor = ArgumentCaptor.forClass(StompHeaders.class);
        ArgumentCaptor<Object> payloadCaptor = ArgumentCaptor.forClass(Object.class);
        verify(ses, times(1)).send(destCaptor.capture(), payloadCaptor.capture());

        Assert.assertEquals(StompDestinationsUtils.buildMetricsDest(metricResponse.clusterId()), destCaptor.getValue().getDestination());
        Assert.assertArrayEquals(new byte[]{1, 2, 3, 4}, (byte[]) payloadCaptor.getValue());
    }
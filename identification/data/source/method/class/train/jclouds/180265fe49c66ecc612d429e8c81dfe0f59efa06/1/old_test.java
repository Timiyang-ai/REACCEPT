@Test
   public void testPutMetricData() throws Exception {
      CloudWatchClient client = createMock(CloudWatchClient.class);
      MetricClient metricClient = createMock(MetricClient.class);
      Set<MetricDatum> metrics = Sets.newLinkedHashSet();
      String namespace = "JCLOUDS/Test";

      for (int i = 0; i < 11; i++) {
         metrics.add(MetricDatum.builder().metricName("foo").build());
      }

      // Using EasyMock.eq("") because EasyMock makes it impossible to pass null as a String value here
      expect(client.getMetricClientForRegion(EasyMock.eq("")))
            .andReturn(metricClient)
            .atLeastOnce();
      
      for (List<MetricDatum> slice : Iterables.partition(metrics, 10)) {
         metricClient.putMetricsInNamespace(slice, namespace);
      }

      EasyMock.replay(client, metricClient);

      CloudWatch.putMetricData(client, "", metrics, namespace);

      EasyMock.verify(metricClient);
   }
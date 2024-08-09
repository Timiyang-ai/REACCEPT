@Test
   public void testPutMetricData() throws Exception {
      CloudWatchClient client = createMock(CloudWatchClient.class);
      MetricClient metricClient = createMock(MetricClient.class);
      Set<MetricDatum> metrics = Sets.newLinkedHashSet();
      String namespace = "JCLOUDS/Test";

      for (int i = 0; i < 11; i++) {
         metrics.add(MetricDatum.builder().build());
      }

      // Using EasyMock.eq("") because EasyMock makes it impossible to pass null as a String value here
      expect(client.getMetricClientForRegion(EasyMock.eq("")))
            .andReturn(metricClient)
            .atLeastOnce();


      metricClient.putMetricData(metrics, namespace);

      expectLastCall().times(2);

      EasyMock.replay(client, metricClient);

      CloudWatch.putMetricData(client, "", metrics, namespace);

      EasyMock.verify(metricClient);
   }
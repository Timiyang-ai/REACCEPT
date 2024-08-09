@Test
   public void testPutMetricData() throws Exception {
      CloudWatchApi api = createMock(CloudWatchApi.class);
      MetricApi metricApi = createMock(MetricApi.class);
      Set<MetricDatum> metrics = Sets.newLinkedHashSet();
      String namespace = "JCLOUDS/Test";

      for (int i = 0; i < 11; i++) {
         metrics.add(MetricDatum.builder().metricName("foo").build());
      }

      // Using EasyMock.eq("") because EasyMock makes it impossible to pass null as a String value here
      expect(api.getMetricApiForRegion(EasyMock.eq("")))
            .andReturn(metricApi)
            .atLeastOnce();
      
      for (List<MetricDatum> slice : Iterables.partition(metrics, 10)) {
         metricApi.putMetricsInNamespace(slice, namespace);
      }

      EasyMock.replay(api, metricApi);

      CloudWatch.putMetricData(api, "", metrics, namespace);

      EasyMock.verify(metricApi);
   }
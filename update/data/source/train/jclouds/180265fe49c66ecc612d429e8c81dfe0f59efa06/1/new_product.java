public static void putMetricData(CloudWatchApi cloudWatchApi, String region, Iterable<MetricDatum> metrics,
            String namespace) {
      MetricApi metricApi = cloudWatchApi.getMetricApiForRegion(region);

      for (List<MetricDatum> slice : Iterables.partition(metrics, 10)) {
         metricApi.putMetricsInNamespace(slice, namespace);
      }
   }
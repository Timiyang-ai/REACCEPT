public static void putMetricData(CloudWatchClient cloudWatchClient, String region, Iterable<MetricDatum> metrics,
            String namespace) {
      MetricClient metricClient = cloudWatchClient.getMetricClientForRegion(region);

      for (List<MetricDatum> slice : Iterables.partition(metrics, 10)) {
         metricClient.putMetricsInNamespace(slice, namespace);
      }
   }
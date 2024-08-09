public static void putMetricData(CloudWatchClient cloudWatchClient, String region, Iterable<MetricDatum> metrics,
                                    String namespace) {
      MetricClient metricClient = cloudWatchClient.getMetricClientForRegion(region);
      Iterator<MetricDatum> mIterator = metrics.iterator();
      Set<MetricDatum> metricsData = Sets.newLinkedHashSet();

      while (mIterator.hasNext()) {
         metricsData.add(mIterator.next());
         if (metricsData.size() == 10 || !mIterator.hasNext()) {
            // Make the call
            metricClient.putMetricData(metrics, namespace);

            // Reset the list for subsequent call if necessary
            if (mIterator.hasNext()) {
               metricsData = Sets.newLinkedHashSet();
            }
         }
      }
   }
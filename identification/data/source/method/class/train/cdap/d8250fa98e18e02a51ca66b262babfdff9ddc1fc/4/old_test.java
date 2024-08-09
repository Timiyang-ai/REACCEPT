  @Test
  public void persistMetricsTests() throws Exception {

    injector.getInstance(TransactionManager.class).startAndWait();
    StructuredTableRegistry structuredTableRegistry = injector.getInstance(StructuredTableRegistry.class);
    structuredTableRegistry.initialize();
    StoreDefinition.createAllTables(injector.getInstance(StructuredTableAdmin.class), structuredTableRegistry);
    injector.getInstance(DatasetOpExecutorService.class).startAndWait();
    injector.getInstance(DatasetService.class).startAndWait();

    Set<Integer> partitions = IntStream.range(0, cConf.getInt(Constants.Metrics.MESSAGING_TOPIC_NUM))
      .boxed().collect(Collectors.toSet());

    long startTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());

    for (int iteration = 0; iteration < 50; iteration++) {
      // First publish all metrics before MessagingMetricsProcessorService starts, so that fetchers of different topics
      // will fetch metrics concurrently.
      for (int i = 0; i < 50; i++) {
        // TOPIC_PREFIX + (i % PARTITION_SIZE) decides which topic the metric is published to
        publishMessagingMetrics(i, startTime, METRICS_CONTEXT, expected, "", MetricType.COUNTER);
      }
      for (int i = 50; i < 100; i++) {
        // TOPIC_PREFIX + (i % PARTITION_SIZE) decides which topic the metric is published to
        publishMessagingMetrics(i, startTime, METRICS_CONTEXT, expected, "", MetricType.GAUGE);
      }

      final MockMetricStore metricStore = new MockMetricStore();
      // Create new MessagingMetricsProcessorService instance every time because the same instance cannot be started
      // again after it's stopped
      MessagingMetricsProcessorService messagingMetricsProcessorService =
        new MessagingMetricsProcessorService(cConf, injector.getInstance(MetricDatasetFactory.class), messagingService,
                                             injector.getInstance(SchemaGenerator.class),
                                             injector.getInstance(DatumReaderFactory.class), metricStore,
                                             partitions, new NoopMetricsContext(), 50, 0);
      messagingMetricsProcessorService.startAndWait();

      // Wait for the 1 aggregated counter metric (with value 50) and 50 gauge metrics to be stored in the metricStore
      Tasks.waitFor(51, () -> metricStore.getAllMetrics().size(), 15, TimeUnit.SECONDS, 100, TimeUnit.MILLISECONDS);

      assertMetricsResult(expected, metricStore.getAllMetrics());

      // validate metrics processor metrics
      // 50 counter and 50 gauge metrics are emitted in each iteration above
      Tasks.waitFor(100L, () -> metricStore.getMetricsProcessedByMetricsProcessor(),
                    15, TimeUnit.SECONDS, 100, TimeUnit.MILLISECONDS);

      // publish a dummy metric
      // this is to force the metrics processor to publish delay metrics for all the topics
      publishMessagingMetrics(100, startTime, METRICS_CONTEXT, expected, "", MetricType.GAUGE);
      // validate the newly published metric
      Tasks.waitFor(101L, () -> metricStore.getMetricsProcessedByMetricsProcessor(),
                    15, TimeUnit.SECONDS, 100, TimeUnit.MILLISECONDS);

      // in MessagingMetricsProcessorService, before persisting the metrics and topic metas, a copy of the topic metas
      // containing the metrics processor delay metrics is made before making a copy of metric values.
      // Therefore, there can be a very small chance where all metric values are persisted but the corresponding
      // topic metas are not yet persisted. Wait for all topic metas to be persisted
      Tasks.waitFor(true, metricStore::isMetricsProcessorDelayEmitted, 15, TimeUnit.SECONDS);

      // Clear metricStore and expected results for the next iteration
      metricStore.deleteAll();
      expected.clear();
      // Stop messagingMetricsProcessorService
      messagingMetricsProcessorService.stopAndWait();
    }
  }
@GuardedBy("monitor")
  @VisibleForTesting
  static MetricServiceClient createMetricServiceClient(
      @Nullable Credentials credentials, Duration deadline) throws IOException {
    MetricServiceSettings.Builder settingsBuilder =
        MetricServiceSettings.newBuilder()
            .setTransportChannelProvider(
                InstantiatingGrpcChannelProvider.newBuilder()
                    .setHeaderProvider(OPENCENSUS_USER_AGENT_HEADER_PROVIDER)
                    .build());
    if (credentials != null) {
      settingsBuilder.setCredentialsProvider(FixedCredentialsProvider.create(credentials));
    }

    org.threeten.bp.Duration stackdriverDuration =
        org.threeten.bp.Duration.ofMillis(deadline.toMillis());
    // We use createMetricDescriptor and createTimeSeries APIs in this exporter.
    settingsBuilder.createMetricDescriptorSettings().setSimpleTimeoutNoRetries(stackdriverDuration);
    settingsBuilder.createTimeSeriesSettings().setSimpleTimeoutNoRetries(stackdriverDuration);

    return MetricServiceClient.create(settingsBuilder.build());
  }
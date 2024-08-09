@GuardedBy("monitor")
  @VisibleForTesting
  static MetricServiceClient createMetricServiceClient(@Nullable Credentials credentials)
      throws IOException {
    MetricServiceSettings.Builder settingsBuilder =
        MetricServiceSettings.newBuilder()
            .setTransportChannelProvider(
                InstantiatingGrpcChannelProvider.newBuilder()
                    .setHeaderProvider(OPENCENSUS_USER_AGENT_HEADER_PROVIDER)
                    .build());
    if (credentials != null) {
      settingsBuilder.setCredentialsProvider(FixedCredentialsProvider.create(credentials));
    }
    return MetricServiceClient.create(settingsBuilder.build());
  }
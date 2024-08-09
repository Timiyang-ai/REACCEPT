  @Test
  public void createMetricServiceClient() throws IOException {
    MetricServiceClient client;
    synchronized (StackdriverStatsExporter.monitor) {
      client =
          StackdriverStatsExporter.createMetricServiceClient(FAKE_CREDENTIALS, DEFAULT_DEADLINE);
    }
    assertThat(client.getSettings().getCredentialsProvider().getCredentials())
        .isEqualTo(FAKE_CREDENTIALS);
    assertThat(client.getSettings().getTransportChannelProvider())
        .isInstanceOf(InstantiatingGrpcChannelProvider.class);
    // There's no way to get HeaderProvider from TransportChannelProvider.
    assertThat(client.getSettings().getTransportChannelProvider().needsHeaders()).isFalse();
  }
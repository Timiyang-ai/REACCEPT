public void heartbeat(final List<Metric> metrics) throws IOException {
    connect();
    try {
      MetricsHeartbeatPRequest.Builder request = MetricsHeartbeatPRequest.newBuilder();
      request.setClientId(MetricsSystem.getAppId());
      request.setHostname(NetworkAddressUtils.getClientHostName());
      request.setOptions(MetricsHeartbeatPOptions.newBuilder().addAllMetrics(metrics).build());
      mClient.metricsHeartbeat(request.build());
    } catch (io.grpc.StatusRuntimeException e) {
      throw new UnavailableException(e);
    }
  }
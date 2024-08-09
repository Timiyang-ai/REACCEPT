public synchronized void heartbeat(List<Metric> metrics) throws IOException {
    connect();
    try {
      mClient.metricsHeartbeat(MetricsSystem.getAppId(), NetworkAddressUtils.getClientHostName(),
          new MetricsHeartbeatTOptions(metrics));
    } catch (AlluxioTException e) {
      throw AlluxioStatusException.fromThrift(e);
    } catch (TException e) {
      throw new UnavailableException(e);
    }
  }
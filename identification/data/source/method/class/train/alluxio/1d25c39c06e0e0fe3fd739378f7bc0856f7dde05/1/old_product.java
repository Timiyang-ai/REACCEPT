public void putClientMetrics(String hostname, String clientId, List<Metric> metrics) {
    if (metrics.isEmpty()) {
      return;
    }
    LOG.debug("Removing metrics for id {} to replace with {}", clientId, metrics);
    synchronized (mClientMetrics) {
      putReportedMetrics(mClientMetrics, getFullInstanceId(hostname, clientId), metrics);
    }
  }
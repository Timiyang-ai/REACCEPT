public void putClientMetrics(String hostname, String clientId,
      List<Metric> metrics) {
    if (metrics.isEmpty()) {
      return;
    }
    putReportedMetrics(mClientMetrics, getFullInstanceId(hostname, clientId), metrics);
  }
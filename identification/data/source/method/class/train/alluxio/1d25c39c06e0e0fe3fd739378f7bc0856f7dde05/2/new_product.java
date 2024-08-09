public synchronized void putClientMetrics(String hostname, String clientId,
      List<Metric> metrics) {
    if (metrics.isEmpty()) {
      return;
    }
    LOG.debug("Removing metrics for id {} to replace with {}", clientId, metrics);
    mClientMetrics.removeByField(ID_INDEX, getFullInstanceId(hostname, clientId));
    for (Metric metric : metrics) {
      if (metric.getHostname() == null) {
        continue; // ignore metrics whose hostname is null
      }
      mClientMetrics.add(metric);
    }
  }
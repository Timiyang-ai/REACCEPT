public synchronized void putClientMetrics(String hostname, String clientId,
      List<Metric> metrics) {
    if (metrics.isEmpty()) {
      return;
    }
    mClientMetrics.removeByField(ID_INDEX, getFullInstanceId(hostname, clientId));
    for (Metric metric : metrics) {
      if (metric.getHostname() == null) {
        continue; // ignore metrics whose hostname is null
      }
      mClientMetrics.add(metric);
    }
  }
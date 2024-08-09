public synchronized void putWorkerMetrics(String hostname, List<Metric> metrics) {
    if (metrics.isEmpty()) {
      return;
    }
    mWorkerMetrics.removeByField(ID_INDEX, getFullInstanceId(hostname, null));
    for (Metric metric : metrics) {
      if (metric.getHostname() == null) {
        continue; // ignore metrics whose hostname is null
      }
      mWorkerMetrics.add(metric);
    }
  }
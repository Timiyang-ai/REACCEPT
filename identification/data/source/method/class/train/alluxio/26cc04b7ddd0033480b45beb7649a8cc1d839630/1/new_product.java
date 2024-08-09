public void putWorkerMetrics(String hostname, List<Metric> metrics) {
    if (metrics.isEmpty()) {
      return;
    }
    synchronized (mWorkerMetrics) {
      mWorkerMetrics.removeByField(ID_INDEX, getFullInstanceId(hostname, null));
      for (Metric metric : metrics) {
        if (metric.getHostname() == null) {
          continue; // ignore metrics whose hostname is null
        }
        mWorkerMetrics.add(metric);
      }
    }
  }
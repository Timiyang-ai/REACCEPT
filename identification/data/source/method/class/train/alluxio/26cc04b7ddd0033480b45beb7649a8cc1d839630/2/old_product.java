public void putWorkerMetrics(String hostname, List<Metric> metrics) {
    if (metrics.isEmpty()) {
      return;
    }
    synchronized (mWorkerMetrics) {
      putReportedMetrics(mWorkerMetrics, getFullInstanceId(hostname, null), metrics);
    }
  }
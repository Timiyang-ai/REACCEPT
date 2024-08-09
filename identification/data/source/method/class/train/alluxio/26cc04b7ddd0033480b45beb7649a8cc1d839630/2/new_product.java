public void putWorkerMetrics(String hostname, List<Metric> metrics) {
    if (metrics.isEmpty()) {
      return;
    }
    putReportedMetrics(mWorkerMetrics, getFullInstanceId(hostname, null), metrics);
  }
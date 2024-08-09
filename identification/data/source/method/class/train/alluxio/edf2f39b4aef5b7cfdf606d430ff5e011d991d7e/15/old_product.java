public void sessionHeartbeat(long sessionId, List<Long> metrics) {
    mSessions.sessionHeartbeat(sessionId);
    mMetricsReporter.updateClientMetrics(metrics);
  }
public void addMetric(String metric, String params, String label) {
        if (metricWasSet) {
            throw new RuntimeException("Old connector don't support multiple metrics");
        }
        metricWasSet = true;
        connector.setMetricType(metric);
    }
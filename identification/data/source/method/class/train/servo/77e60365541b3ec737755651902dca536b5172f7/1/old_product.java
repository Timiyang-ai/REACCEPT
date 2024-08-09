public void update(List<Metric> metrics) {
    Preconditions.checkNotNull(metrics, "metrics");
    LOGGER.debug("received {} metrics", metrics.size());
    final List<Metric> newMetrics = new ArrayList<Metric>(metrics.size());
    for (Metric m : metrics) {
      if (isCounter(m)) {
        final MonitorConfig rateConfig = toRateConfig(m.getConfig());
        final CounterValue prev = cache.get(rateConfig);
        if (prev != null) {
          final double rate = prev.computeRate(m);
          newMetrics.add(new Metric(rateConfig, m.getTimestamp(), rate));
        } else {
          CounterValue current = new CounterValue(m);
          cache.put(rateConfig, current);
          if (intervalMillis > 0L) {
            final double delta = m.getNumberValue().doubleValue();
            final double rate = current.computeRate(intervalMillis, delta);
            newMetrics.add(new Metric(rateConfig, m.getTimestamp(), rate));
          }
        }
      } else {
        newMetrics.add(m);
      }
    }
    LOGGER.debug("writing {} metrics to downstream observer", newMetrics.size());
    observer.update(newMetrics);
  }
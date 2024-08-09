@GET
  @Path(GET_METRICS)
  @ReturnType("java.util.Map<String, Long>")
  public Response getMetrics() {
    MetricRegistry metricRegistry = mMaster.getMasterMetricsSystem().getMetricRegistry();

    // Get all counters.
    Map<String, Counter> counters = metricRegistry.getCounters();

    // Only the gauge for pinned files is retrieved here, other gauges are statistics of free/used
    // spaces, those statistics can be gotten via other REST apis.
    String filesPinnedProperty = CommonUtils.argsToString(".",
        MasterContext.getMasterSource().getName(), MasterSource.FILES_PINNED);
    Gauge filesPinned = metricRegistry.getGauges().get(filesPinnedProperty);

    // Get values of the counters and gauges and put them into a metrics map.
    Map<String, Long> metrics = new HashMap<>(counters.size() + 1);
    for (Map.Entry<String, Counter> counter : counters.entrySet()) {
      metrics.put(counter.getKey(), counter.getValue().getCount());
    }
    metrics.put(filesPinnedProperty, ((Integer) filesPinned.getValue()).longValue());

    return Response.ok(metrics).build();
  }
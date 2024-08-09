@GET
  @Path(GET_METRICS)
  @ReturnType("java.util.SortedMap<java.lang.String, java.lang.Long>")
  @Deprecated
  public Response getMetrics() {
    return RestUtils.call(new RestUtils.RestCallable<Map<String, Long>>() {
      @Override
      public Map<String, Long> call() throws Exception {
        return getMetricsInternal();
      }
    });
  }
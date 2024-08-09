@GET
  @Path(GET_METRICS)
  @ReturnType("java.util.SortedMap<java.lang.String, java.lang.Long>")
  @Deprecated
  public Response getMetrics() {
    return RestUtils.call(this::getMetricsInternal);
  }
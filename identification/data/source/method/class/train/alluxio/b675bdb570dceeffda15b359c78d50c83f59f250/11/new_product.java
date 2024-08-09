@GET
  @Path(GET_METRICS)
  @Deprecated
  public Response getMetrics() {
    return RestUtils.call(this::getMetricsInternal, ServerConfiguration.global());
  }
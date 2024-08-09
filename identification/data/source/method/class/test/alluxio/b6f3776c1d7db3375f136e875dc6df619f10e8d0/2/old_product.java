@GET
  @Path(GET_UPTIME_MS)
  @ReturnType("java.lang.Long")
  @Deprecated
  public Response getUptimeMs() {
    return RestUtils.call(() -> mMasterProcess.getUptimeMs(), ServerConfiguration.global());
  }
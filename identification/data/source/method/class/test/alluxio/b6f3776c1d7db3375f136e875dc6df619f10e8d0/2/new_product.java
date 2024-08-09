@GET
  @Path(GET_UPTIME_MS)
  @Deprecated
  public Response getUptimeMs() {
    return RestUtils.call(() -> mMasterProcess.getUptimeMs(), ServerConfiguration.global());
  }
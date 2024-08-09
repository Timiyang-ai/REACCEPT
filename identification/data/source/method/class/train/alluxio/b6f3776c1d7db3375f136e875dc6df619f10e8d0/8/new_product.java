@GET
  @Path(GET_UPTIME_MS)
  @ReturnType("java.lang.Long")
  public Response getUptimeMs() {
    return RestUtils.createResponse(mMaster.getUptimeMs());
  }
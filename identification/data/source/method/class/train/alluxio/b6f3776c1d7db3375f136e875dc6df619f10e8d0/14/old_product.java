@GET
  @Path(GET_UPTIME_MS)
  @ReturnType("java.lang.Long")
  public Response getUptimeMs() {
    return Response.ok(System.currentTimeMillis() - mMaster.getStarttimeMs()).build();
  }
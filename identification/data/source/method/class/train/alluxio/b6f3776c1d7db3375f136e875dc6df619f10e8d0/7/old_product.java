@GET
  @Path(GET_START_TIME_MS)
  @ReturnType("java.lang.Long")
  @Deprecated
  public Response getStartTimeMs() {
    return RestUtils.call(() -> mMasterProcess.getStartTimeMs());
  }
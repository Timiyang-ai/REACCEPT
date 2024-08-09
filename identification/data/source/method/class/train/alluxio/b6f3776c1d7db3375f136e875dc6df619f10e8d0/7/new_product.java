@GET
  @Path(GET_START_TIME_MS)
  @Deprecated
  public Response getStartTimeMs() {
    return RestUtils.call(() -> mMasterProcess.getStartTimeMs());
  }
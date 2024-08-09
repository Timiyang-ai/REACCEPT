@GET
  @Path(GET_START_TIME_MS)
  @ReturnType("java.lang.Long")
  public Response getStartTimeMs() {
    return Response.ok(mMaster.getStartTimeMs()).build();
  }
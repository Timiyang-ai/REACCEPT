@GET
  @Path(GET_START_TIME_MS)
  @ReturnType("java.lang.Long")
  public Response getStartTimeMs() {
    return RestUtils.call(new RestUtils.RestCallable<Long>() {
      @Override
      public Long call() throws Exception {
        return mMaster.getStartTimeMs();
      }
    });
  }
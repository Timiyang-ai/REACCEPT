@GET
  @Path(GET_START_TIME_MS)
  @Deprecated
  public Response getStartTimeMs() {
    return RestUtils.call(new RestUtils.RestCallable<Long>() {
      @Override
      public Long call() throws Exception {
        return mMasterProcess.getStartTimeMs();
      }
    });
  }
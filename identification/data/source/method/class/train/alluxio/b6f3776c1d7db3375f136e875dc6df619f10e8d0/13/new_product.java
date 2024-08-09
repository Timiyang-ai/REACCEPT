@GET
  @Path(GET_UPTIME_MS)
  @Deprecated
  public Response getUptimeMs() {
    return RestUtils.call(new RestUtils.RestCallable<Long>() {
      @Override
      public Long call() throws Exception {
        return mMasterProcess.getUptimeMs();
      }
    });
  }
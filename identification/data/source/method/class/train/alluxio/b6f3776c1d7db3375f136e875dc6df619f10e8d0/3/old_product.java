@GET
  @Path(GET_UPTIME_MS)
  @ReturnType("java.lang.Long")
  public Response getUptimeMs() {
    return RestUtils.call(new RestUtils.RestCallable() {
      @Override
      public Object call() throws Exception {
        return mMaster.getUptimeMs();
      }
    });
  }
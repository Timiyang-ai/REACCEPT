@GET
  @Path(GET_VERSION)
  @Deprecated
  public Response getVersion() {
    return RestUtils.call(new RestUtils.RestCallable<String>() {
      @Override
      public String call() throws Exception {
        return RuntimeConstants.VERSION;
      }
    });
  }
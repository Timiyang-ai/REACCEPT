@GET
  @Path(GET_RPC_ADDRESS)
  @ReturnType("java.lang.String")
  @Deprecated
  public Response getRpcAddress() {
    return RestUtils.call(new RestUtils.RestCallable<String>() {
      @Override
      public String call() throws Exception {
        return mMasterProcess.getRpcAddress().toString();
      }
    });
  }
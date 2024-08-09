@GET
  @Path(GET_RPC_ADDRESS)
  @ReturnType("java.lang.String")
  public Response getRpcAddress() {
    return RestUtils.call(new RestUtils.RestCallable<String>() {
      @Override
      public String call() throws Exception {
        return mMaster.getMasterAddress().toString();
      }
    });
  }
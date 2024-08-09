@GET
  @Path(GET_RPC_ADDRESS)
  @ReturnType("java.lang.String")
  @Deprecated
  public Response getRpcAddress() {
    return RestUtils.call(() -> mMasterProcess.getRpcAddress().toString());
  }
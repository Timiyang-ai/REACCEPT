@GET
  @Path(GET_RPC_ADDRESS)
  @Deprecated
  public Response getRpcAddress() {
    return RestUtils
        .call(() -> mMasterProcess.getRpcAddress().toString(), ServerConfiguration.global());
  }
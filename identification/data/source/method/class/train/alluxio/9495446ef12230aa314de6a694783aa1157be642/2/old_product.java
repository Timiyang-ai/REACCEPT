@GET
  @Path(GET_RPC_ADDRESS)
  @ReturnType("java.lang.String")
  public Response getRpcAddress() {
    return RestUtils.createResponse(mMaster.getMasterAddress().toString());
  }
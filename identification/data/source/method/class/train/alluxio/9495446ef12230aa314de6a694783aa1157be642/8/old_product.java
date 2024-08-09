@GET
  @Path(GET_RPC_ADDRESS)
  @ReturnType("java.lang.String")
  public Response getRpcAddress() {
    return Response.ok(mMaster.getMasterAddress().toString()).build();
  }
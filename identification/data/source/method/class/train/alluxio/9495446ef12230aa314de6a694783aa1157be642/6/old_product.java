@GET
  @Path(GET_RPC_ADDRESS)
  @ReturnType("java.lang.String")
  public Response getRpcAddress() {
    // Need to encode the string as JSON because Jackson will not do it automatically.
    return Response.ok(FormatUtils.encodeJson(mMaster.getMasterAddress().toString())).build();
  }
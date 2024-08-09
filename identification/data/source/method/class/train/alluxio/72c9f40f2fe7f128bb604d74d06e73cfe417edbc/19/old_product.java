@GET
  @Path(GET_USED_BYTES)
  @ReturnType("java.lang.Long")
  public Response getUsedBytes() {
    return RestUtils.createResponse(mBlockMaster.getUsedBytes());
  }
@GET
  @Path(GET_FREE_BYTES)
  @ReturnType("java.lang.Long")
  public Response getFreeBytes() {
    return RestUtils.createResponse(mBlockMaster.getCapacityBytes() - mBlockMaster.getUsedBytes());
  }
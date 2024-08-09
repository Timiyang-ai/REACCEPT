@GET
  @Path(GET_CAPACITY_BYTES)
  @ReturnType("java.lang.Long")
  public Response getCapacityBytes() {
    return RestUtils.createResponse(mBlockMaster.getCapacityBytes());
  }
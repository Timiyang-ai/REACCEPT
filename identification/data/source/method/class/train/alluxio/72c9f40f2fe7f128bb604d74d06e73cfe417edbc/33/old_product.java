@GET
  @Path(GET_FREE_BYTES)
  @ReturnType("java.lang.Long")
  public Response getFreeBytes() {
    return Response.ok(mBlockMaster.getCapacityBytes() - mBlockMaster.getUsedBytes()).build();
  }
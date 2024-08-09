@GET
  @Path(GET_FREE_BYTES)
  @ReturnType("java.lang.Long")
  @Deprecated
  public Response getFreeBytes() {
    return RestUtils.call(() -> mBlockMaster.getCapacityBytes() - mBlockMaster.getUsedBytes());
  }
@GET
  @Path(GET_CAPACITY_BYTES)
  @ReturnType("java.lang.Long")
  @Deprecated
  public Response getCapacityBytes() {
    return RestUtils.call(() -> mBlockMaster.getCapacityBytes(), ServerConfiguration.global());
  }
@GET
  @Path(GET_CAPACITY_BYTES)
  @Deprecated
  public Response getCapacityBytes() {
    return RestUtils.call(() -> mBlockMaster.getCapacityBytes(), ServerConfiguration.global());
  }
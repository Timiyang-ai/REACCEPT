@GET
  @Path(GET_FREE_BYTES)
  @Deprecated
  public Response getFreeBytes() {
    return RestUtils.call(() -> mBlockMaster.getCapacityBytes() - mBlockMaster.getUsedBytes(),
        ServerConfiguration.global());
  }
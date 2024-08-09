@GET
  @Path(GET_USED_BYTES)
  @Deprecated
  public Response getUsedBytes() {
    return RestUtils.call(() -> mBlockMaster.getUsedBytes(), ServerConfiguration.global());
  }
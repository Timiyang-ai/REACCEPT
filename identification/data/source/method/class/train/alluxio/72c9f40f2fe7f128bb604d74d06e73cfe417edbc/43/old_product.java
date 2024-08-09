@GET
  @Path(GET_USED_BYTES)
  @ReturnType("java.lang.Long")
  @Deprecated
  public Response getUsedBytes() {
    return RestUtils.call(() -> mBlockMaster.getUsedBytes());
  }
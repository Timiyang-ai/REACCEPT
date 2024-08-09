@GET
  @Path(GET_UFS_USED_BYTES)
  @Deprecated
  public Response getUfsUsedBytes() {
    return RestUtils.call(() -> getUfsCapacityInternal().getUsed());
  }
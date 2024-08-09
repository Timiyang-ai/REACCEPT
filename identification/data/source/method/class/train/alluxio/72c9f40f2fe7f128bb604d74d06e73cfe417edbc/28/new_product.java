@GET
  @Path(GET_UFS_CAPACITY_BYTES)
  @ReturnType("java.lang.Long")
  @Deprecated
  public Response getUfsCapacityBytes() {
    return RestUtils.call(() -> getUfsCapacityInternal().getTotal());
  }
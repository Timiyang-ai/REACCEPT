@GET
  @Path(GET_UFS_CAPACITY_BYTES)
  @Deprecated
  public Response getUfsCapacityBytes() {
    return RestUtils.call(() -> getUfsCapacityInternal().getTotal());
  }
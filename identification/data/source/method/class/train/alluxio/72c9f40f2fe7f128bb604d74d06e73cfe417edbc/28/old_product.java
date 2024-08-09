@GET
  @Path(GET_UFS_CAPACITY_BYTES)
  @ReturnType("java.lang.Long")
  @Deprecated
  public Response getUfsCapacityBytes() {
    return RestUtils.call(() -> mUfs.getSpace(mUfsRoot, UnderFileSystem.SpaceType.SPACE_USED));
  }
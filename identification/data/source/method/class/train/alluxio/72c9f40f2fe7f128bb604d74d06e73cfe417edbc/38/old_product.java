@GET
  @Path(GET_UFS_USED_BYTES)
  @ReturnType("java.lang.Long")
  @Deprecated
  public Response getUfsUsedBytes() {
    return RestUtils.call(() -> mUfs.getSpace(mUfsRoot, UnderFileSystem.SpaceType.SPACE_USED));
  }
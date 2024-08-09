@GET
  @Path(GET_UFS_FREE_BYTES)
  @ReturnType("java.lang.Long")
  @Deprecated
  public Response getUfsFreeBytes() {
    return RestUtils.call(() -> mUfs.getSpace(mUfsRoot, UnderFileSystem.SpaceType.SPACE_FREE));
  }
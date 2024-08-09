@GET
  @Path(GET_UFS_FREE_BYTES)
  @ReturnType("java.lang.Long")
  @Deprecated
  public Response getUfsFreeBytes() {
    return RestUtils.call(new RestUtils.RestCallable<Long>() {
      @Override
      public Long call() throws Exception {
        return mUfs.getSpace(mUfsRoot, UnderFileSystem.SpaceType.SPACE_FREE);
      }
    });
  }
@GET
  @Path(GET_UFS_USED_BYTES)
  @Deprecated
  public Response getUfsUsedBytes() {
    return RestUtils.call(new RestUtils.RestCallable<Long>() {
      @Override
      public Long call() throws Exception {
        return mUfs.getSpace(mUfsRoot, UnderFileSystem.SpaceType.SPACE_USED);
      }
    });
  }
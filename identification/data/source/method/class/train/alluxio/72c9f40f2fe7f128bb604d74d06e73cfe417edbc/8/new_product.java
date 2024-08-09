@GET
  @Path(GET_UFS_FREE_BYTES)
  @Deprecated
  public Response getUfsFreeBytes() {
    return RestUtils.call(new RestUtils.RestCallable<Long>() {
      @Override
      public Long call() throws Exception {
        return mUfs.getSpace(mUfsRoot, UnderFileSystem.SpaceType.SPACE_FREE);
      }
    });
  }
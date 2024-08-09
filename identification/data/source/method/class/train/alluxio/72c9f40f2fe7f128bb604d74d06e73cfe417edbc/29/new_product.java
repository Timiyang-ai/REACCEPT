@GET
  @Path(GET_UFS_CAPACITY_BYTES)
  @Deprecated
  public Response getUfsCapacityBytes() {
    return RestUtils.call(new RestUtils.RestCallable<Long>() {
      @Override
      public Long call() throws Exception {
        return mUfs.getSpace(mUfsRoot, UnderFileSystem.SpaceType.SPACE_TOTAL);
      }
    });
  }
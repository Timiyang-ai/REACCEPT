@GET
  @Path(GET_UFS_CAPACITY_BYTES)
  @ReturnType("java.lang.Long")
  public Response getUfsCapacityBytes() {
    return RestUtils.call(new RestUtils.RestCallable() {
      @Override
      public Object call() throws Exception {
        return mUfs.getSpace(mUfsRoot, UnderFileSystem.SpaceType.SPACE_TOTAL);
      }
    });
  }
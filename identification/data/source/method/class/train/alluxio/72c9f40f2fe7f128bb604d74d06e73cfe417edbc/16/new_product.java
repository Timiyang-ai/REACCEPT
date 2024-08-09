@GET
  @Path(GET_UFS_USED_BYTES)
  @ReturnType("java.lang.Long")
  public Response getUfsUsedBytes() {
    return RestUtils.call(new RestUtils.RestCallable() {
      @Override
      public Object call() throws Exception {
        return mUfs.getSpace(mUfsRoot, UnderFileSystem.SpaceType.SPACE_USED);
      }
    });
  }
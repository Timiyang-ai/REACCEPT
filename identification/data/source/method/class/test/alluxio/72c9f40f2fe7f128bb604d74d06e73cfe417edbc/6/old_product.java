@GET
  @Path(GET_UFS_USED_BYTES)
  @ReturnType("java.lang.Long")
  @Deprecated
  public Response getUfsUsedBytes() {
    return RestUtils.call(new RestUtils.RestCallable<Long>() {
      @Override
      public Long call() throws Exception {
        return getUfsCapacityInternal().getUsed();
      }
    });
  }
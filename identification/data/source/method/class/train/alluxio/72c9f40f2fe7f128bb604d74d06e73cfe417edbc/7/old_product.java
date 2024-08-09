@GET
  @Path(GET_FREE_BYTES)
  @ReturnType("java.lang.Long")
  @Deprecated
  public Response getFreeBytes() {
    return RestUtils.call(new RestUtils.RestCallable<Long>() {
      @Override
      public Long call() throws Exception {
        return mBlockMaster.getCapacityBytes() - mBlockMaster.getUsedBytes();
      }
    });
  }
@GET
  @Path(GET_FREE_BYTES)
  @ReturnType("java.lang.Long")
  public Response getFreeBytes() {
    return RestUtils.call(new RestUtils.RestCallable() {
      @Override
      public Object call() throws Exception {
        return mBlockMaster.getCapacityBytes() - mBlockMaster.getUsedBytes();
      }
    });
  }
@GET
  @Path(GET_CAPACITY_BYTES)
  @ReturnType("java.lang.Long")
  public Response getCapacityBytes() {
    return RestUtils.call(new RestUtils.RestCallable<Long>() {
      @Override
      public Long call() throws Exception {
        return mBlockMaster.getCapacityBytes();
      }
    });
  }
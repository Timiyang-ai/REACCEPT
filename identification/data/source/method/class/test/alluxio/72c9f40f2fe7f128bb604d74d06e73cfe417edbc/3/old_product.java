@GET
  @Path(GET_USED_BYTES)
  @ReturnType("java.lang.Long")
  @Deprecated
  public Response getUsedBytes() {
    return RestUtils.call(new RestUtils.RestCallable<Long>() {
      @Override
      public Long call() throws Exception {
        return getCapacityInternal().getUsed();
      }
    });
  }
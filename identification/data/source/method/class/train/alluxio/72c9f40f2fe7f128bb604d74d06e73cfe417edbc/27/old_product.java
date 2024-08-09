@GET
  @Path(GET_UFS_CAPACITY_BYTES)
  @ReturnType("java.lang.Long")
  @Deprecated
  public Response getUfsCapacityBytes() {
    return RestUtils.call(new RestUtils.RestCallable<Long>() {
      @Override
      public Long call() throws Exception {
        return getUfsCapacityInternal().getTotal();
      }
    });
  }
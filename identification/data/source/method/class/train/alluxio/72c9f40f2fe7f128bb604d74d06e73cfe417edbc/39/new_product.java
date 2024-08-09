@GET
  @Path(GET_USED_BYTES)
  @Deprecated
  public Response getUsedBytes() {
    return RestUtils.call(new RestUtils.RestCallable<Long>() {
      @Override
      public Long call() throws Exception {
        return mBlockMaster.getUsedBytes();
      }
    });
  }
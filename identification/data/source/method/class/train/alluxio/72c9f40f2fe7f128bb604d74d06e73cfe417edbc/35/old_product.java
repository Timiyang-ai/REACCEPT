@GET
  @Path(GET_WORKER_COUNT)
  @ReturnType("java.lang.Integer")
  @Deprecated
  public Response getWorkerCount() {
    return RestUtils.call(new RestUtils.RestCallable<Integer>() {
      @Override
      public Integer call() throws Exception {
        return mBlockMaster.getWorkerCount();
      }
    });
  }
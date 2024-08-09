@GET
  @Path(GET_WORKER_COUNT)
  @ReturnType("java.lang.Integer")
  public Response getWorkerCount() {
    return RestUtils.call(new RestUtils.RestCallable() {
      @Override
      public Object call() throws Exception {
        return mBlockMaster.getWorkerCount();
      }
    });
  }
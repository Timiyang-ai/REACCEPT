@GET
  @Path(GET_WORKER_INFO_LIST)
  @ReturnType("java.util.List<alluxio.wire.WorkerInfo>")
  public Response getWorkerInfoList() {
    return RestUtils.call(new RestUtils.RestCallable() {
      @Override
      public Object call() throws Exception {
        return mBlockMaster.getWorkerInfoList();
      }
    });
  }
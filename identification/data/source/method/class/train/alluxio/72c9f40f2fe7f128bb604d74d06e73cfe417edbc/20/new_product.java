@GET
  @Path(GET_WORKER_INFO_LIST)
  @ReturnType("java.util.List<alluxio.wire.WorkerInfo>")
  public Response getWorkerInfoList() {
    return RestUtils.call(new RestUtils.RestCallable<List<WorkerInfo>>() {
      @Override
      public List<WorkerInfo> call() throws Exception {
        return mBlockMaster.getWorkerInfoList();
      }
    });
  }
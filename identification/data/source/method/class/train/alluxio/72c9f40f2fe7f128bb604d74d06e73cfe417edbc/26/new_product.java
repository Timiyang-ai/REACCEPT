@GET
  @Path(GET_WORKER_INFO_LIST)
  @Deprecated
  public Response getWorkerInfoList() {
    return RestUtils.call(new RestUtils.RestCallable<List<WorkerInfo>>() {
      @Override
      public List<WorkerInfo> call() throws Exception {
        return mBlockMaster.getWorkerInfoList();
      }
    });
  }
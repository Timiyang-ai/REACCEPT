@GET
  @Path(GET_WORKER_INFO_LIST)
  @ReturnType("java.util.List<alluxio.wire.WorkerInfo>")
  @Deprecated
  public Response getWorkerInfoList() {
    return RestUtils.call(()-> mBlockMaster.getWorkerInfoList());
  }
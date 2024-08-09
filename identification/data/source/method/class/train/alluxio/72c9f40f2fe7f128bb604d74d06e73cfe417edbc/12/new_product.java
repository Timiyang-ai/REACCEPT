@GET
  @Path(GET_WORKER_INFO_LIST)
  @Deprecated
  public Response getWorkerInfoList() {
    return RestUtils.call(() -> mBlockMaster.getWorkerInfoList(), ServerConfiguration.global());
  }
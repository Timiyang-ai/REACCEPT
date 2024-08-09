@GET
  @Path(GET_WORKER_COUNT)
  @Deprecated
  public Response getWorkerCount() {
    return RestUtils.call(()->mBlockMaster.getWorkerCount());
  }
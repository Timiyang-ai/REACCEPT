@GET
  @Path(GET_WORKER_COUNT)
  @ReturnType("java.lang.Integer")
  @Deprecated
  public Response getWorkerCount() {
    return RestUtils.call(()->mBlockMaster.getWorkerCount());
  }
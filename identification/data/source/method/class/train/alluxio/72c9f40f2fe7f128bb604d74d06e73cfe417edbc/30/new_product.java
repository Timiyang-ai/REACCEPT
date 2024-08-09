@GET
  @Path(GET_WORKER_COUNT)
  @ReturnType("java.lang.Integer")
  public Response getWorkerCount() {
    return RestUtils.createResponse(mBlockMaster.getWorkerCount());
  }
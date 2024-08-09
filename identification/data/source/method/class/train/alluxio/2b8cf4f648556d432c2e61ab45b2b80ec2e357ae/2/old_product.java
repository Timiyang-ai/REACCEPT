@POST
  @Path(LOCK_BLOCK)
  @Produces(MediaType.APPLICATION_JSON)
  @ReturnType("alluxio.wire.LockBlockResult")
  public Response lockBlock(@QueryParam("sessionId") final Long sessionId,
      @QueryParam("blockId") final Long blockId) {
    return RestUtils.call(new RestUtils.RestCallable<LockBlockResult>() {
      @Override
      public LockBlockResult call() throws Exception {
        Preconditions.checkNotNull(blockId, "required 'blockId' parameter is missing");
        Preconditions.checkNotNull(sessionId, "required 'sessionId' parameter is missing");
        long lockId = mBlockWorker.lockBlock(sessionId, blockId);
        return new LockBlockResult().setLockId(lockId)
            .setBlockPath(mBlockWorker.readBlock(sessionId, blockId, lockId));
      }
    });
  }
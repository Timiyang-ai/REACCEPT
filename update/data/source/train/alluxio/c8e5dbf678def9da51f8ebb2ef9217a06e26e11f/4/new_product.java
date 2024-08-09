String createBlock(long sessionId, long blockId, String tierAlias,
      String medium, long initialBytes)
      throws BlockAlreadyExistsException, WorkerOutOfSpaceException, IOException;
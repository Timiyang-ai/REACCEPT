public synchronized void addCheckpoint(int fileId, long length, String checkpointPath,
      long opTimeMs) {
    if (INACTIVE) {
      return;
    }

    EditLogOperation operation =
        new EditLogOperation(EditLogOperationType.ADD_CHECKPOINT, ++mTransactionId)
            .withParameter("fileId", fileId).withParameter("length", length)
            .withParameter("path", checkpointPath).withParameter("opTimeMs", opTimeMs);
    writeOperation(operation);
  }
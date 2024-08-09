public synchronized void addCheckpoint(int fileId, long length, TachyonURI checkpointPath,
      long opTimeMs) {
    if (INACTIVE) {
      return;
    }

    EditLogOperation operation =
        new EditLogOperation(EditLogOperationType.ADD_CHECKPOINT, ++mTransactionId)
            .withParameter("fileId", fileId).withParameter("length", length)
            .withParameter("path", checkpointPath.getPath()).withParameter("opTimeMs", opTimeMs);
    writeOperation(operation);
  }
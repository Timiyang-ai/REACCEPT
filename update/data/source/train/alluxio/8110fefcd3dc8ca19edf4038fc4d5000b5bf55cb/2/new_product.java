public InputStream getInputStreamAtPosition(long tempUfsFileId, long position)
      throws FileDoesNotExistException, IOException {
    InputStreamAgent agent;
    synchronized (mInputStreamAgents) {
      agent = mInputStreamAgents.getFirstByField(mInputIdIndex, tempUfsFileId);
    }
    if (agent == null) {
      throw new FileDoesNotExistException(
          ExceptionMessage.BAD_WORKER_FILE_ID.getMessage(tempUfsFileId));
    }
    return agent.openAtPosition(position);
  }
public OutputStream getOutputStream(long tempUfsFileId) throws FileDoesNotExistException {
    OutputStreamAgent agent;
    synchronized (mOutputStreamAgents) {
      agent = mOutputStreamAgents.getFirstByField(mOutputIdIndex, tempUfsFileId);
    }
    if (agent == null) {
      throw new FileDoesNotExistException(
          ExceptionMessage.BAD_WORKER_FILE_ID.getMessage(tempUfsFileId));
    }
    return agent.getStream();
  }
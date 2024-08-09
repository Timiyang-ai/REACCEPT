public OutputStream getOutputStream(long tempUfsFileId) throws FileDoesNotExistException {
    OutputStreamAgent stream = mOutputAgents.get(tempUfsFileId);
    if (stream == null) {
      throw new FileDoesNotExistException(
          ExceptionMessage.BAD_WORKER_FILE_ID.getMessage(tempUfsFileId));
    }
    return stream.getStream();
  }
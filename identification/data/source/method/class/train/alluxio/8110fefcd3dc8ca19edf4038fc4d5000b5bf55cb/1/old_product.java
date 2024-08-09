public InputStream getInputStreamAtPosition(long tempUfsFileId, long position)
      throws FileDoesNotExistException, IOException {
    InputStreamAgent stream = mInputAgents.get(tempUfsFileId);
    if (stream == null) {
      throw new FileDoesNotExistException(
          ExceptionMessage.BAD_WORKER_FILE_ID.getMessage(tempUfsFileId));
    }
    return stream.openAtPosition(position);
  }
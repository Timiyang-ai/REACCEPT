@Override
  public int read() throws IOException {
    if (mPosition == mLength) { // at end of file
      return -1;
    }
    CountingRetry retry = new CountingRetry(mBlockWorkerClientReadRetry);
    IOException lastException = null;
    while (retry.attempt()) {
      try {
        updateStream();
        int result = mBlockInStream.read();
        if (result != -1) {
          mPosition++;
        }
        return result;
      } catch (UnavailableException | DeadlineExceededException | ConnectException e) {
        lastException = e;
        if (mBlockInStream != null) {
          handleRetryableException(mBlockInStream, e);
          mBlockInStream = null;
        }
      }
    }
    throw lastException;
  }
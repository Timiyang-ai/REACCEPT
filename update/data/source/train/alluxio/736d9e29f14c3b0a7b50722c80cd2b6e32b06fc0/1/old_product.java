public synchronized String lockBlock(final long blockId) throws IOException {
    // TODO(jiri) Would be nice to have a helper method to execute this try-catch logic
    try {
      return retryRPC(new RpcCallableThrowsTachyonTException<String>() {
        @Override
        public String call() throws TachyonTException, TException {
          return mClient.lockBlock(blockId, mSessionId);
        }
      });
    } catch (TachyonException e) {
      if (e.getType() == TachyonExceptionType.FILE_DOES_NOT_EXIST) {
        return null;
      } else {
        throw new IOException(e);
      }
    }
  }
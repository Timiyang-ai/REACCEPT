synchronized void addCheckpoint(long fid) throws IOException {
    mWorkerClient.addCheckpoint(fid, 0);
  }
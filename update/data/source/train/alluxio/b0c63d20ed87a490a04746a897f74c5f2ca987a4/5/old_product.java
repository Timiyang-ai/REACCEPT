synchronized void addCheckpoint(int fid) throws IOException {
    mWorkerClient.addCheckpoint(fid);
  }
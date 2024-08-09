public synchronized void removeWorker(long workerId) {
    mWorkerIdToAlias.remove(workerId);
  }
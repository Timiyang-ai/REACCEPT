public synchronized void addWorker(long workerId, int tierAlias) {
    mWorkerIdToAlias.put(workerId, tierAlias);
  }
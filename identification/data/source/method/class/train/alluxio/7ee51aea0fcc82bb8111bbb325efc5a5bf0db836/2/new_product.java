public synchronized void addWorker(long workerId, String tierAlias) {
    mWorkerIdToAlias.put(workerId, tierAlias);
  }
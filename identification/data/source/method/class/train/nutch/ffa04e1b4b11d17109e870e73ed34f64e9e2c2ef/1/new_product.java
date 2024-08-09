public int fetch(String batchId, int threads, boolean shouldResume,
      int numTasks) throws Exception {
    return fetch(batchId, threads, shouldResume, numTasks, false, false);
  }
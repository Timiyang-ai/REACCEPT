public int fetch(String batchId, int threads, boolean shouldResume,
      int numTasks) throws Exception {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    long start = System.currentTimeMillis();
    LOG.info("FetcherJob: starting at " + sdf.format(start));

    if (batchId.equals(Nutch.ALL_BATCH_ID_STR)) {
      LOG.info("FetcherJob: fetching all");
    } else {
      LOG.info("FetcherJob: batchId: " + batchId);
    }

    run(ToolUtil.toArgMap(Nutch.ARG_BATCH, batchId, Nutch.ARG_THREADS, threads,
        Nutch.ARG_RESUME, shouldResume, Nutch.ARG_NUMTASKS, numTasks));

    long finish = System.currentTimeMillis();
    LOG.info("FetcherJob: finished at " + sdf.format(finish)
        + ", time elapsed: " + TimingUtil.elapsedTime(start, finish));

    return 0;
  }
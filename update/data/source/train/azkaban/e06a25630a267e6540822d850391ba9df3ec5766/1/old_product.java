@Override
  public List<Executor> fetchActiveExecutors() throws ExecutorManagerException {
    QueryRunner runner = createQueryRunner();
    FetchExecutorHandler executorHandler = new FetchExecutorHandler();

    try {
      List<Executor> executors =
        runner.query(FetchExecutorHandler.FETCH_ACTIVE_EXECUTORS,
          executorHandler);
      return executors;
    } catch (SQLException e) {
      throw new ExecutorManagerException("Error fetching active executors", e);
    }
  }
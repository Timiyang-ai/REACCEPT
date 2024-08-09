@Test
  public void testGetFlow() throws IOException, ExecutorManagerException,
      InterruptedException {
    final QueuedExecutions queue = new QueuedExecutions(2);
    final List<Pair<ExecutionReference, ExecutableFlow>> dataList = getDummyData();
    queue.enqueueAll(dataList);
    for (final Pair<ExecutionReference, ExecutableFlow> pair : dataList) {
      Assert.assertEquals(pair.getSecond(),
          queue.getFlow(pair.getFirst().getExecId()));
    }
  }
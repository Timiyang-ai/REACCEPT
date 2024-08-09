@Test
  public void testGetFlow() throws IOException, ExecutorManagerException,
    InterruptedException {
    QueuedExecutions queue = new QueuedExecutions(2);
    List<Pair<ExecutionReference, ExecutableFlow>> dataList = getDummyData();
    queue.enqueueAll(dataList);
    for (Pair<ExecutionReference, ExecutableFlow> pair : dataList) {
      Assert.assertEquals(pair.getSecond(),
        queue.getFlow(pair.getFirst().getExecId()));
    }
  }
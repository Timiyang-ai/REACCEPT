@Test
  public void testHasExecution() throws IOException, ExecutorManagerException,
    InterruptedException {
    QueuedExecutions queue = new QueuedExecutions(2);
    List<Pair<ExecutionReference, ExecutableFlow>> dataList = getDummyData();
    queue.enqueueAll(dataList);
    for (Pair<ExecutionReference, ExecutableFlow> pair : dataList) {
      Assert.assertTrue(queue.hasExecution(pair.getFirst().getExecId()));
    }
    Assert.assertFalse(queue.hasExecution(5));
    Assert.assertFalse(queue.hasExecution(7));
    Assert.assertFalse(queue.hasExecution(15));
  }
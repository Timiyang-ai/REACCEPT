@Test
  public void testIsFull() throws IOException, ExecutorManagerException,
    InterruptedException {
    QueuedExecutions queue = new QueuedExecutions(2);
    List<Pair<ExecutionReference, ExecutableFlow>> dataList = getDummyData();
    queue.enqueueAll(dataList);
    Assert.assertTrue(queue.isFull());
  }
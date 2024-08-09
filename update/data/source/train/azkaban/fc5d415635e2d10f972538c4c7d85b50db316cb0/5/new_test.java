@Test
  public void testIsFull() throws IOException, ExecutorManagerException,
      InterruptedException {
    final QueuedExecutions queue = new QueuedExecutions(2);
    final List<Pair<ExecutionReference, ExecutableFlow>> dataList = getDummyData();
    queue.enqueueAll(dataList);
    Assert.assertTrue(queue.isFull());
  }
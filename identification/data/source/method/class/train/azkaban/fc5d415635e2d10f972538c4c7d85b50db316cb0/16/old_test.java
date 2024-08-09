@Test
  public void testClear() throws IOException, ExecutorManagerException {
    QueuedExecutions queue = new QueuedExecutions(5);
    List<Pair<ExecutionReference, ExecutableFlow>> dataList = getDummyData();
    queue.enqueueAll(dataList);
    Assert.assertEquals(queue.size(), 2);
    queue.clear();
    Assert.assertEquals(queue.size(), 0);
  }
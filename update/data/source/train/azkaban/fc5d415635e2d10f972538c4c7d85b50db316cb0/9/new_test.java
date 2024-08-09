@Test
  public void testSize() throws IOException, ExecutorManagerException {
    final QueuedExecutions queue = new QueuedExecutions(5);
    final List<Pair<ExecutionReference, ExecutableFlow>> dataList = getDummyData();
    queue.enqueueAll(dataList);
    Assert.assertEquals(queue.size(), 2);
  }
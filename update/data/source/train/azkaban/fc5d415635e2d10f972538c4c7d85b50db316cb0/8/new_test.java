@Test
  public void testEnqueueAll() throws IOException, ExecutorManagerException {
    final QueuedExecutions queue = new QueuedExecutions(5);
    final List<Pair<ExecutionReference, ExecutableFlow>> dataList = getDummyData();
    queue.enqueueAll(dataList);
    Assert.assertTrue(queue.getAllEntries().containsAll(dataList));
    Assert.assertTrue(dataList.containsAll(queue.getAllEntries()));
  }
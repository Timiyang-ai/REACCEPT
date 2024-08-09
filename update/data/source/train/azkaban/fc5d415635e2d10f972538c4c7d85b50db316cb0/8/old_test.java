@Test
  public void testEnqueueAll() throws IOException, ExecutorManagerException {
    QueuedExecutions queue = new QueuedExecutions(5);
    List<Pair<ExecutionReference, ExecutableFlow>> dataList = getDummyData();
    queue.enqueueAll(dataList);
    Assert.assertTrue(queue.getAllEntries().containsAll(dataList));
    Assert.assertTrue(dataList.containsAll(queue.getAllEntries()));
  }
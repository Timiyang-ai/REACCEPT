@Test
  public void testIsEmpty() throws IOException, ExecutorManagerException {
    final QueuedExecutions queue = new QueuedExecutions(5);
    final List<Pair<ExecutionReference, ExecutableFlow>> dataList = getDummyData();
    Assert.assertTrue(queue.isEmpty());
    queue.enqueueAll(dataList);
    Assert.assertEquals(queue.size(), 2);
    queue.clear();
    Assert.assertTrue(queue.isEmpty());
  }
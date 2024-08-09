@Test
  public void testIsEmpty() throws IOException, ExecutorManagerException {
    QueuedExecutions queue = new QueuedExecutions(5);
    List<Pair<ExecutionReference, ExecutableFlow>> dataList = getDummyData();
    Assert.assertTrue(queue.isEmpty());
    queue.enqueueAll(dataList);
    Assert.assertEquals(queue.size(), 2);
    queue.clear();
    Assert.assertTrue(queue.isEmpty());
  }
@Test
  public void testFetchHead() throws IOException, ExecutorManagerException,
      InterruptedException {
    final QueuedExecutions queue = new QueuedExecutions(5);
    final List<Pair<ExecutionReference, ExecutableFlow>> dataList = getDummyData();
    Assert.assertTrue(queue.isEmpty());
    queue.enqueueAll(dataList);
    Assert.assertEquals(queue.fetchHead(), dataList.get(0));
    Assert.assertEquals(queue.fetchHead(), dataList.get(1));
  }
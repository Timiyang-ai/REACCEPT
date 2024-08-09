@Test
  public void testDequeue() throws IOException, ExecutorManagerException {
    QueuedExecutions queue = new QueuedExecutions(5);
    List<Pair<ExecutionReference, ExecutableFlow>> dataList = getDummyData();
    queue.enqueueAll(dataList);
    queue.dequeue(dataList.get(0).getFirst().getExecId());
    Assert.assertEquals(queue.size(), 1);
    Assert.assertTrue(queue.getAllEntries().contains(dataList.get(1)));
  }
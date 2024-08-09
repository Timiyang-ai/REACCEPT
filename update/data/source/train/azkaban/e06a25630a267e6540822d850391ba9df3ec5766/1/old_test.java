@Test
  public void testFetchActiveExecutors() throws Exception {
    if (!isTestSetup()) {
      return;
    }
    ExecutorLoader loader = createLoader();
    List<Executor> executors = addTestExecutors(loader);

    loader.inactivateExecutor(executors.get(0).getId());

    List<Executor> fetchedExecutors = loader.fetchActiveExecutors();
    Assert.assertEquals(executors.size(), fetchedExecutors.size() + 1);
    executors.remove(0);

    Assert.assertArrayEquals(executors.toArray(), fetchedExecutors.toArray());
    clearDB();
  }
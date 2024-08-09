@Test
  public void testFetchActiveExecutors() throws Exception {
    if (!isTestSetup()) {
      return;
    }
    ExecutorLoader loader = createLoader();
    List<Executor> executors = addTestExecutors(loader);

    executors.get(0).setActive(false);
    loader.updateExecutor(executors.get(0));

    List<Executor> fetchedExecutors = loader.fetchActiveExecutors();
    Assert.assertEquals(executors.size(), fetchedExecutors.size() + 1);
    executors.remove(0);

    Assert.assertArrayEquals(executors.toArray(), fetchedExecutors.toArray());
    clearDB();
  }
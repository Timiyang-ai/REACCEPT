@Test
  public void localEndpoint_provisionsOnce() throws Exception {
    // create all the tasks up front so that they are executed with no delay
    List<Callable<Endpoint>> tasks = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      tasks.add(() -> platform.localEndpoint());
    }

    ExecutorService executor = Executors.newFixedThreadPool(tasks.size());
    List<Future<Endpoint>> futures = executor.invokeAll(tasks);

    // check there's only a single unique endpoint returned
    Set<Object> results = Sets.newIdentityHashSet();
    for (Future<Endpoint> future : futures) {
      results.add(future.get());
    }
    assertThat(results).hasSize(1);

    executor.shutdownNow();
  }
  @Test
  public void scheduledExecutorService_default() {
    InProcessServerBuilder builder = InProcessServerBuilder.forName("foo");
    InProcessServer server =
        Iterables.getOnlyElement(builder.buildTransportServers(new ArrayList<Factory>()));

    ObjectPool<ScheduledExecutorService> scheduledExecutorServicePool =
        server.getScheduledExecutorServicePool();
    ObjectPool<ScheduledExecutorService> expectedPool =
        SharedResourcePool.forResource(TIMER_SERVICE);

    ScheduledExecutorService expected = expectedPool.getObject();
    ScheduledExecutorService actual = scheduledExecutorServicePool.getObject();
    assertSame(expected, actual);

    expectedPool.returnObject(expected);
    scheduledExecutorServicePool.returnObject(actual);
  }
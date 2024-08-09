  @Test
  public void getConf() throws Exception {
    ServerConfiguration.set(PropertyKey.WORKER_MEMORY_SIZE, "2048");
    ClientContext ctx = ClientContext.create(ServerConfiguration.global());
    assertEquals(0, GetConf.getConf(ctx,
        PropertyKey.WORKER_MEMORY_SIZE.toString()));
    assertEquals("2048\n", mOutputStream.toString());

    mOutputStream.reset();
    ServerConfiguration.set(PropertyKey.WORKER_MEMORY_SIZE, "2MB");
    ctx = ClientContext.create(ServerConfiguration.global());
    assertEquals(0, GetConf.getConf(ctx,
        PropertyKey.WORKER_MEMORY_SIZE.toString()));
    assertEquals("2MB\n", mOutputStream.toString());

    mOutputStream.reset();
    ServerConfiguration.set(PropertyKey.WORKER_MEMORY_SIZE, "Nonsense");
    ctx = ClientContext.create(ServerConfiguration.global());
    assertEquals(0, GetConf.getConf(ctx,
        PropertyKey.WORKER_MEMORY_SIZE.toString()));
    assertEquals("Nonsense\n", mOutputStream.toString());
  }
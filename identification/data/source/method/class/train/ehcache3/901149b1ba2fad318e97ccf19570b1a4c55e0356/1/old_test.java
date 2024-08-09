@Test
  public void testConfigure() throws Exception {
    OffHeapIdentifierRegistry registry = new OffHeapIdentifierRegistry(32, MemoryUnit.MEGABYTES);

    final EhcacheActiveEntity activeEntity = new EhcacheActiveEntity(registry, ENTITY_ID);
    ClientDescriptor client = new TestClientDescriptor();
    activeEntity.connected(client);
    assertThat(activeEntity.getConnectedClients().keySet(), contains(client));

    assertSuccess(activeEntity.invoke(client,
        MESSAGE_FACTORY.configureCacheManager(new ServerSideConfigBuilder()
            .defaultResource("defaultServerResource")
            .sharedPool("primary", "serverResource1", 4, MemoryUnit.MEGABYTES)
            .sharedPool("secondary", "serverResource2", 8, MemoryUnit.MEGABYTES)
            .build())));

    assertThat(activeEntity.getSharedResourcePoolIds(), containsInAnyOrder("primary", "secondary"));

    assertThat(registry.getResource("serverResource1").getUsed(), is(MemoryUnit.MEGABYTES.toBytes(4L)));
    assertThat(registry.getResource("serverResource2").getUsed(), is(MemoryUnit.MEGABYTES.toBytes(8L)));
    assertThat(registry.getResource("defaultServerResource").getUsed(), is(0L));

    assertThat(activeEntity.getConnectedClients().get(client), is(Matchers.<String>empty()));
    assertThat(activeEntity.getInUseStores().keySet(), is(Matchers.<String>empty()));
    assertThat(activeEntity.getStores(), is(Matchers.<String>empty()));
  }
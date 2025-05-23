@Test
  public void testConfigure() throws Exception {
    OffHeapIdentifierRegistry registry = new OffHeapIdentifierRegistry(32, MemoryUnit.MEGABYTES);
    registry.addResource("defaultServerResource", 8, MemoryUnit.MEGABYTES);
    registry.addResource("serverResource1", 8, MemoryUnit.MEGABYTES);
    registry.addResource("serverResource2", 8, MemoryUnit.MEGABYTES);

    final EhcacheActiveEntity activeEntity = new EhcacheActiveEntity(registry, ENTITY_ID);
    ClientDescriptor client = new TestClientDescriptor();
    activeEntity.connected(client);
    assertThat(activeEntity.getConnectedClients().keySet(), contains(client));

    assertSuccess(activeEntity.invoke(client,
        MESSAGE_FACTORY.configureStoreManager(new ServerSideConfigBuilder()
            .defaultResource("defaultServerResource")
            .sharedPool("primary", "serverResource1", 4, MemoryUnit.MEGABYTES)
            .sharedPool("secondary", "serverResource2", 8, MemoryUnit.MEGABYTES)
            .build())));

    assertThat(registry.getStoreManagerService().getSharedResourcePoolIds(), containsInAnyOrder("primary", "secondary"));

    assertThat(registry.getResource("serverResource1").getUsed(), is(MemoryUnit.MEGABYTES.toBytes(4L)));
    assertThat(registry.getResource("serverResource2").getUsed(), is(MemoryUnit.MEGABYTES.toBytes(8L)));
    assertThat(registry.getResource("defaultServerResource").getUsed(), is(0L));

    assertThat(activeEntity.getConnectedClients().get(client), is(Matchers.<String>empty()));
    assertThat(activeEntity.getInUseStores().keySet(), is(Matchers.<String>empty()));
    assertThat(registry.getStoreManagerService().getStores(), is(Matchers.<String>empty()));
  }
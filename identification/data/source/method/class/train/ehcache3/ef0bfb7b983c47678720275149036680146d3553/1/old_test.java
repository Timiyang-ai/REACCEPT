@Test
  public void testConfigure() throws Exception {
    final ServiceRegistry registry = new OffHeapIdentifierRegistry();
    final EhcacheActiveEntity activeEntity = new EhcacheActiveEntity(registry, ENTITY_ID);

    final Map<String, Pool> resourcePools = new HashMap<String, Pool>();
    resourcePools.put("primary", new Pool("serverResource1", MemoryUnit.MEGABYTES.toBytes(4L)));
    resourcePools.put("secondary", new Pool("serverResource2", MemoryUnit.MEGABYTES.toBytes(8L)));

    final ConfigureCacheManager configureMessage =
        (ConfigureCacheManager)EhcacheEntityMessage.configure(new ServerSideConfiguration("defaultServerResource", resourcePools));

    ClientDescriptor client = new TestClientDescriptor();
    assertSuccess(activeEntity.invoke(client, configureMessage));

    final Set<String> poolIds = activeEntity.getSharedResourcePoolIds();
    assertThat(poolIds, containsInAnyOrder("primary", "secondary"));
  }
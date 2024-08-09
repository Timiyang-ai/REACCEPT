@Test
  public void testInvalidateCachedControllerLeader() {
    HelixManager helixManager = mock(HelixManager.class);
    HelixDataAccessor helixDataAccessor = mock(HelixDataAccessor.class);
    BaseDataAccessor<ZNRecord> baseDataAccessor = mock(BaseDataAccessor.class);
    HelixAdmin helixAdmin = mock(HelixAdmin.class);
    ZNRecord znRecord = mock(ZNRecord.class);
    final String leaderHost = "host";
    final int leaderPort = 12345;

    // Lead controller resource disabled.
    ConfigAccessor configAccessor = mock(ConfigAccessor.class);
    ResourceConfig resourceConfig = mock(ResourceConfig.class);
    when(helixManager.getConfigAccessor()).thenReturn(configAccessor);
    when(configAccessor.getResourceConfig(anyString(), anyString())).thenReturn(resourceConfig);
    when(resourceConfig.getSimpleConfig(anyString())).thenReturn("false");

    when(helixManager.getHelixDataAccessor()).thenReturn(helixDataAccessor);
    when(helixDataAccessor.getBaseDataAccessor()).thenReturn(baseDataAccessor);
    when(znRecord.getId()).thenReturn(leaderHost + "_" + leaderPort);
    when(baseDataAccessor.get(anyString(), any(), anyInt())).thenReturn(znRecord);
    when(helixManager.getClusterName()).thenReturn("testCluster");
    when(helixManager.getClusterManagmentTool()).thenReturn(helixAdmin);
    when(helixAdmin.getResourceExternalView(anyString(), anyString())).thenReturn(null);

    // Create Controller Leader Locator
    FakeControllerLeaderLocator.create(helixManager);
    ControllerLeaderLocator controllerLeaderLocator = FakeControllerLeaderLocator.getInstance();

    // check values at startup
    Assert.assertTrue(controllerLeaderLocator.isCachedControllerLeaderInvalid());
    Assert.assertEquals(controllerLeaderLocator.getLastCacheInvalidateMillis(), 0);

    // very first invalidate
    controllerLeaderLocator.invalidateCachedControllerLeader();
    Assert.assertTrue(controllerLeaderLocator.isCachedControllerLeaderInvalid());
    long lastCacheInvalidateMillis = controllerLeaderLocator.getLastCacheInvalidateMillis();
    Assert.assertTrue(lastCacheInvalidateMillis > 0);

    // invalidate within {@link ControllerLeaderLocator::getMillisBetweenInvalidate()} millis
    // values should remain unchanged
    lastCacheInvalidateMillis = System.currentTimeMillis();
    controllerLeaderLocator.setLastCacheInvalidateMillis(lastCacheInvalidateMillis);
    controllerLeaderLocator.invalidateCachedControllerLeader();
    Assert.assertTrue(controllerLeaderLocator.isCachedControllerLeaderInvalid());
    Assert.assertEquals(controllerLeaderLocator.getLastCacheInvalidateMillis(), lastCacheInvalidateMillis);

    // getControllerLeader, which validates the cache
    controllerLeaderLocator.getControllerLeader(testTable);
    Assert.assertFalse(controllerLeaderLocator.isCachedControllerLeaderInvalid());
    Assert.assertEquals(controllerLeaderLocator.getLastCacheInvalidateMillis(), lastCacheInvalidateMillis);

    // invalidate within {@link ControllerLeaderLocator::getMillisBetweenInvalidate()} millis
    // values should remain unchanged
    lastCacheInvalidateMillis = System.currentTimeMillis();
    controllerLeaderLocator.setLastCacheInvalidateMillis(lastCacheInvalidateMillis);
    controllerLeaderLocator.invalidateCachedControllerLeader();
    Assert.assertFalse(controllerLeaderLocator.isCachedControllerLeaderInvalid());
    Assert.assertEquals(controllerLeaderLocator.getLastCacheInvalidateMillis(), lastCacheInvalidateMillis);

    // invalidate after {@link ControllerLeaderLocator::getMillisBetweenInvalidate()} millis have elapsed, by setting lastCacheInvalidateMillis to well before the millisBetweenInvalidate
    // cache should be invalidated and last cache invalidation time should get updated
    lastCacheInvalidateMillis = System.currentTimeMillis() - 2 * controllerLeaderLocator.getMillisBetweenInvalidate();
    controllerLeaderLocator.setLastCacheInvalidateMillis(lastCacheInvalidateMillis);
    controllerLeaderLocator.invalidateCachedControllerLeader();
    Assert.assertTrue(controllerLeaderLocator.isCachedControllerLeaderInvalid());
    Assert.assertTrue(controllerLeaderLocator.getLastCacheInvalidateMillis() > lastCacheInvalidateMillis);
  }
@Test
  public void testInvalidateCachedControllerLeader() {
    HelixManager helixManager = mock(HelixManager.class);
    HelixDataAccessor helixDataAccessor = mock(HelixDataAccessor.class);
    final String leaderHost = "host";
    final int leaderPort = 12345;

    // Lead controller resource disabled.
    ConfigAccessor configAccessor = mock(ConfigAccessor.class);
    ResourceConfig resourceConfig = mock(ResourceConfig.class);
    when(helixManager.getConfigAccessor()).thenReturn(configAccessor);
    when(configAccessor.getResourceConfig(any(), anyString())).thenReturn(resourceConfig);
    when(resourceConfig.getSimpleConfig(anyString())).thenReturn("false");

    // Mocks the helix leader
    when(helixManager.getHelixDataAccessor()).thenReturn(helixDataAccessor);
    PropertyKey.Builder keyBuilder = mock(PropertyKey.Builder.class);
    when(helixDataAccessor.keyBuilder()).thenReturn(keyBuilder);
    PropertyKey controllerLeader = mock(PropertyKey.class);
    when(keyBuilder.controllerLeader()).thenReturn(controllerLeader);
    LiveInstance liveInstance = mock(LiveInstance.class);
    when(helixDataAccessor.getProperty(controllerLeader)).thenReturn(liveInstance);
    when(liveInstance.getInstanceName()).thenReturn(leaderHost + "_" + leaderPort);

    // Create Controller Leader Locator
    FakeControllerLeaderLocator.create(helixManager);
    FakeControllerLeaderLocator controllerLeaderLocator = FakeControllerLeaderLocator.getInstance();

    // check values at startup
    Assert.assertFalse(controllerLeaderLocator.isCachedControllerLeaderValid());
    Assert.assertEquals(controllerLeaderLocator.getLastCacheInvalidationTimeMs(), 0);

    // very first invalidate
    long currentTimeMs = 31_000L;
    controllerLeaderLocator.setCurrentTimeMs(currentTimeMs);
    controllerLeaderLocator.invalidateCachedControllerLeader();
    Assert.assertFalse(controllerLeaderLocator.isCachedControllerLeaderValid());
    long lastCacheInvalidateMillis = controllerLeaderLocator.getLastCacheInvalidationTimeMs();
    Assert.assertTrue(lastCacheInvalidateMillis > 0);
    Assert.assertEquals(lastCacheInvalidateMillis, currentTimeMs);

    // invalidate within {@link ControllerLeaderLocator::getMinInvalidateIntervalMs()} millis
    // values should remain unchanged
    currentTimeMs = 32_000L;
    controllerLeaderLocator.setCurrentTimeMs(currentTimeMs);
    controllerLeaderLocator.invalidateCachedControllerLeader();
    Assert.assertFalse(controllerLeaderLocator.isCachedControllerLeaderValid());
    Assert.assertEquals(controllerLeaderLocator.getLastCacheInvalidationTimeMs(), lastCacheInvalidateMillis);

    // getControllerLeader, which validates the cache
    controllerLeaderLocator.getControllerLeader(testTable);
    Assert.assertTrue(controllerLeaderLocator.isCachedControllerLeaderValid());
    Assert.assertEquals(controllerLeaderLocator.getLastCacheInvalidationTimeMs(), lastCacheInvalidateMillis);

    // invalidate within {@link ControllerLeaderLocator::getMinInvalidateIntervalMs()} millis
    // values should remain unchanged
    currentTimeMs = 33_000L;
    controllerLeaderLocator.setCurrentTimeMs(currentTimeMs);
    controllerLeaderLocator.invalidateCachedControllerLeader();
    Assert.assertTrue(controllerLeaderLocator.isCachedControllerLeaderValid());
    Assert.assertEquals(controllerLeaderLocator.getLastCacheInvalidationTimeMs(), lastCacheInvalidateMillis);

    // invalidate after {@link ControllerLeaderLocator::getMinInvalidateIntervalMs()} millis have elapsed, by setting lastCacheInvalidateMillis to well before the millisBetweenInvalidate
    // cache should be invalidated and last cache invalidation time should get updated
    controllerLeaderLocator.setCurrentTimeMs(
        controllerLeaderLocator.getCurrentTimeMs() + 2 * controllerLeaderLocator.getMinInvalidateIntervalMs());
    controllerLeaderLocator.invalidateCachedControllerLeader();
    Assert.assertFalse(controllerLeaderLocator.isCachedControllerLeaderValid());
    Assert.assertTrue(controllerLeaderLocator.getLastCacheInvalidationTimeMs() > lastCacheInvalidateMillis);
  }
@Test
  public void testManageTopology() throws Exception {
    Config config = Mockito.mock(Config.class);
    Mockito.when(config.getStringValue(ConfigKeys.get("TOPOLOGY_NAME"))).thenReturn(TOPOLOGY_NAME);

    RuntimeManagerMain runtimeManagerMain =
        Mockito.spy(new RuntimeManagerMain(config, MOCK_COMMAND));

    // Failed to create state manager instance
    final String CLASS_NOT_EXIST = "class_not_exist";
    Mockito.when(config.getStringValue(ConfigKeys.get("STATE_MANAGER_CLASS"))).
        thenReturn(CLASS_NOT_EXIST);
    Assert.assertFalse(runtimeManagerMain.manageTopology());

    // Valid state manager class
    Mockito.when(config.getStringValue(ConfigKeys.get("STATE_MANAGER_CLASS"))).
        thenReturn(NullStateManager.class.getName());

    // Failed to valid
    Mockito.doReturn(false).when(runtimeManagerMain).
        validateRuntimeManage(Mockito.any(SchedulerStateManagerAdaptor.class),
            Mockito.eq(TOPOLOGY_NAME));
    Assert.assertFalse(runtimeManagerMain.manageTopology());

    // Legal request
    Mockito.doReturn(true).when(runtimeManagerMain).
        validateRuntimeManage(Mockito.any(SchedulerStateManagerAdaptor.class),
            Mockito.eq(TOPOLOGY_NAME));

    // Failed to get ISchedulerClient
    Mockito.doReturn(null).when(runtimeManagerMain).
        getSchedulerClient(Mockito.any(Config.class));
    Assert.assertFalse(runtimeManagerMain.manageTopology());

    // Successfully get ISchedulerClient
    ISchedulerClient client = Mockito.mock(ISchedulerClient.class);
    Mockito.doReturn(client).when(runtimeManagerMain).
        getSchedulerClient(Mockito.any(Config.class));

    // Failed to callRuntimeManagerRunner
    Mockito.doReturn(false).when(runtimeManagerMain).
        callRuntimeManagerRunner(Mockito.any(Config.class), Mockito.eq(client));
    Assert.assertFalse(runtimeManagerMain.manageTopology());

    // Happy path
    Mockito.doReturn(true).when(runtimeManagerMain).
        callRuntimeManagerRunner(Mockito.any(Config.class), Mockito.eq(client));
    Assert.assertTrue(runtimeManagerMain.manageTopology());
  }
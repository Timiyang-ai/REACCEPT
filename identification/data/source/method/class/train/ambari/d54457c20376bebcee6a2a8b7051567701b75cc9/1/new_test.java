@Test
  public void testExecuteDMLUpdates() throws Exception {
    Method removeNagiosService = UpgradeCatalog200.class.getDeclaredMethod("removeNagiosService");
    Method updateHiveDatabaseType = UpgradeCatalog200.class.getDeclaredMethod("updateHiveDatabaseType");
    Method addNewConfigurationsFromXml = AbstractUpgradeCatalog.class.getDeclaredMethod
        ("addNewConfigurationsFromXml");
    Method updateTezConfiguration = UpgradeCatalog200.class.getDeclaredMethod("updateTezConfiguration");
    Method updateClusterEnvConfiguration = UpgradeCatalog200.class.getDeclaredMethod("updateClusterEnvConfiguration");
    Method updateConfigurationProperties = AbstractUpgradeCatalog.class.getDeclaredMethod
            ("updateConfigurationProperties", String.class, Map.class, boolean.class, boolean.class);
    Method persistHDPRepo = UpgradeCatalog200.class.getDeclaredMethod("persistHDPRepo");

    UpgradeCatalog200 upgradeCatalog = createMockBuilder(UpgradeCatalog200.class)
        .addMockedMethod(removeNagiosService)
        .addMockedMethod(updateHiveDatabaseType)
        .addMockedMethod(addNewConfigurationsFromXml)
        .addMockedMethod(updateTezConfiguration)
        .addMockedMethod(updateConfigurationProperties)
        .addMockedMethod(updateClusterEnvConfiguration)
        .addMockedMethod(persistHDPRepo)
        .createMock();

    upgradeCatalog.removeNagiosService();
    expectLastCall().once();
    upgradeCatalog.addNewConfigurationsFromXml();
    expectLastCall();
    
    upgradeCatalog.updateHiveDatabaseType();
    expectLastCall().once();

    upgradeCatalog.updateTezConfiguration();
    expectLastCall().once();

    upgradeCatalog.updateConfigurationProperties("hive-site",
            Collections.singletonMap("hive.server2.transport.mode", "binary"), false, false);
    expectLastCall();

    upgradeCatalog.persistHDPRepo();
    expectLastCall().once();

    upgradeCatalog.updateClusterEnvConfiguration();
    expectLastCall();

    replay(upgradeCatalog);

    upgradeCatalog.executeDMLUpdates();

    verify(upgradeCatalog);
  }
@Test
  public void testExecuteDMLUpdates() throws Exception {
    Method removeNagiosService = UpgradeCatalog200.class.getDeclaredMethod("removeNagiosService");
    Method updateHiveDatabaseType = UpgradeCatalog200.class.getDeclaredMethod("updateHiveDatabaseType");
    Method addNewConfigurationsFromXml = AbstractUpgradeCatalog.class.getDeclaredMethod
        ("addNewConfigurationsFromXml");
    Method setSecurityType = UpgradeCatalog200.class.getDeclaredMethod("setSecurityType");
    Method updateConfigurationProperties = AbstractUpgradeCatalog.class.getDeclaredMethod
            ("updateConfigurationProperties", String.class, Map.class, boolean.class, boolean.class);

    UpgradeCatalog200 upgradeCatalog = createMockBuilder(UpgradeCatalog200.class)
        .addMockedMethod(removeNagiosService)
        .addMockedMethod(updateHiveDatabaseType)
        .addMockedMethod(addNewConfigurationsFromXml)
        .addMockedMethod(setSecurityType)
        .addMockedMethod(updateConfigurationProperties)
        .createMock();

    upgradeCatalog.removeNagiosService();
    expectLastCall().once();
    upgradeCatalog.addNewConfigurationsFromXml();
    expectLastCall();
    
    upgradeCatalog.updateHiveDatabaseType();
    expectLastCall().once();
    upgradeCatalog.setSecurityType();
    expectLastCall().once();

    upgradeCatalog.updateConfigurationProperties("hive-site",
            Collections.singletonMap("hive.server2.transport.mode", "binary"), false, false);
    expectLastCall();

    replay(upgradeCatalog);

    upgradeCatalog.executeDMLUpdates();

    verify(upgradeCatalog);
  }
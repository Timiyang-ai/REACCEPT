@Test
  public void testExecuteDMLUpdates() throws Exception {
    Method removeNagiosService = UpgradeCatalog200.class.getDeclaredMethod("removeNagiosService");
    Method updateHiveDatabaseType = UpgradeCatalog200.class.getDeclaredMethod("updateHiveDatabaseType");
    Method addNewConfigurationsFromXml = AbstractUpgradeCatalog.class.getDeclaredMethod
        ("addNewConfigurationsFromXml");
    Method setSecurityType = UpgradeCatalog200.class.getDeclaredMethod("setSecurityType");
    Method updateDfsClusterAdmintistratorsProperty = UpgradeCatalog200.class.getDeclaredMethod("updateDfsClusterAdmintistratorsProperty");

    UpgradeCatalog200 upgradeCatalog = createMockBuilder(UpgradeCatalog200.class)
        .addMockedMethod(removeNagiosService)
        .addMockedMethod(updateHiveDatabaseType)
        .addMockedMethod(addNewConfigurationsFromXml)
        .addMockedMethod(setSecurityType)
        .addMockedMethod(updateDfsClusterAdmintistratorsProperty)
        .createMock();

    upgradeCatalog.removeNagiosService();
    expectLastCall().once();
    upgradeCatalog.addNewConfigurationsFromXml();
    expectLastCall();

    upgradeCatalog.updateDfsClusterAdmintistratorsProperty();
    expectLastCall();
    
    upgradeCatalog.updateHiveDatabaseType();
    expectLastCall().once();
    upgradeCatalog.setSecurityType();
    expectLastCall().once();

    replay(upgradeCatalog);

    upgradeCatalog.executeDMLUpdates();

    verify(upgradeCatalog);
  }
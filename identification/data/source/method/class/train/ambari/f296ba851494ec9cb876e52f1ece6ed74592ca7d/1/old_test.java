@Test
  public void testExecuteDMLUpdates() throws Exception {
    Method removeNagiosService = UpgradeCatalog200.class.getDeclaredMethod("removeNagiosService");
    Method updateHiveDatabaseType = UpgradeCatalog200.class.getDeclaredMethod("updateHiveDatabaseType");
    Method addNewConfigurationsFromXml = AbstractUpgradeCatalog.class.getDeclaredMethod
        ("addNewConfigurationsFromXml");

    UpgradeCatalog200 upgradeCatalog = createMockBuilder(
        UpgradeCatalog200.class).addMockedMethod(removeNagiosService).addMockedMethod(updateHiveDatabaseType).addMockedMethod(addNewConfigurationsFromXml).createMock();

    upgradeCatalog.removeNagiosService();
    expectLastCall().once();
    upgradeCatalog.addNewConfigurationsFromXml();
    expectLastCall();
    upgradeCatalog.updateHiveDatabaseType();
    expectLastCall().once();


    replay(upgradeCatalog);

    upgradeCatalog.executeDMLUpdates();

    verify(upgradeCatalog);
  }
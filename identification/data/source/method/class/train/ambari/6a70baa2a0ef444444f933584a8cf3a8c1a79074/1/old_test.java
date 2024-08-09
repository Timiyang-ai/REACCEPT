@Test
  public void testExecuteDMLUpdates() throws Exception {
    Method removeNagiosService = UpgradeCatalog200.class.getDeclaredMethod("removeNagiosService");
    Method updateHiveDatabaseType = UpgradeCatalog200.class.getDeclaredMethod("updateHiveDatabaseType");

    UpgradeCatalog200 upgradeCatalog = createMockBuilder(
        UpgradeCatalog200.class).addMockedMethod(removeNagiosService).addMockedMethod(updateHiveDatabaseType).createMock();

    upgradeCatalog.removeNagiosService();
    expectLastCall().once();
    upgradeCatalog.updateHiveDatabaseType();
    expectLastCall().once();


    replay(upgradeCatalog);

    upgradeCatalog.executeDMLUpdates();

    verify(upgradeCatalog);
  }
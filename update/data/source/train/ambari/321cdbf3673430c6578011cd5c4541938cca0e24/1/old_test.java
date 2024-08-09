@Test
  public void testExecuteDMLUpdates() throws Exception {
    Method removeNagiosService = UpgradeCatalog200.class.getDeclaredMethod("removeNagiosService");

    UpgradeCatalog200 upgradeCatalog = createMockBuilder(
        UpgradeCatalog200.class).addMockedMethod(removeNagiosService).createMock();

    upgradeCatalog.removeNagiosService();
    expectLastCall().once();

    replay(upgradeCatalog);

    upgradeCatalog.executeDMLUpdates();

    verify(upgradeCatalog);
  }
@Test
  public final void testGetCache() {
    ShowMissingDiskStoresFunction smdsFunc = new ShowMissingDiskStoresFunction();
    assertTrue(smdsFunc.getCache() instanceof Cache);
  }
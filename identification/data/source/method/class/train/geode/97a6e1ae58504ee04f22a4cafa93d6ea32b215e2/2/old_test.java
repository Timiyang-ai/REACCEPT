@Test
  public final void testGetId() {
    ShowMissingDiskStoresFunction smdsFunc = new ShowMissingDiskStoresFunction();
    assertEquals(ShowMissingDiskStoresFunction.class.getName(), smdsFunc.getId());
  }
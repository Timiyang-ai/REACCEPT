@Test
  public void testGetId() {
    ShowMissingDiskStoresFunction smdsFunc = new ShowMissingDiskStoresFunction();
    assertEquals(ShowMissingDiskStoresFunction.class.getName(), smdsFunc.getId());
  }
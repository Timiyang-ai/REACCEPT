@Test
  public void findEntriesSinceTest() throws StoreException {
    FindInfo findInfo = store.findEntriesSince(new StoreFindToken(), Long.MAX_VALUE);
    Set<StoreKey> keysPresent = new HashSet<>();
    for (MessageInfo info : findInfo.getMessageEntries()) {
      keysPresent.add(info.getStoreKey());
    }
    assertEquals("All keys were not present in the return from findEntriesSince()", allKeys.keySet(), keysPresent);
  }
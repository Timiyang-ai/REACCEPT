@Test
  public void findEntriesSinceTest() throws StoreException {
    FindInfo findInfo = store.findEntriesSince(new StoreFindToken(), Long.MAX_VALUE);
    Set<StoreKey> keysPresent = new HashSet<>();
    for (MessageInfo info : findInfo.getMessageEntries()) {
      keysPresent.add(info.getStoreKey());
    }
    assertEquals("All keys were not present in the return from findEntriesSince()", allKeys.keySet(), keysPresent);

    // Extra Test: findEntriesSince method can correctly capture disk related IO error and shutdown store if needed.
    store.shutdown();
    catchStoreExceptionAndVerifyErrorCode(
        (blobStore) -> blobStore.findEntriesSince(new StoreFindToken(), Long.MAX_VALUE));
    reloadStore();
  }
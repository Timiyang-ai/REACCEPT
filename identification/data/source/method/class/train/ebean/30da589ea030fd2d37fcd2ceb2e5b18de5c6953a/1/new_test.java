  @ForPlatform({Platform.H2})
  @Test
  public void get() {
    Ebean.execute(() -> {
      SpiTransaction txn = getInScopeTransaction();
      assertNotNull(txn);
    });

    // thread local should be set to null
    assertNull(getInScopeTransaction());
  }
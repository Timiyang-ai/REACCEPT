  @After
  public void cleanup() throws Exception {
    if (state != null) {
      state.destroy();
    }
    assertTrue(tempDir + " could not be cleaned", StoreTestUtils.cleanDirectory(tempDir, true));
  }
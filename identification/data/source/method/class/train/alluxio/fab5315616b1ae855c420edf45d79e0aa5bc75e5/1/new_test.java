  @Test
  public void flush() throws IOException {
    assertFalse(mUnderStorageFlushed.get());
    mTestStream.flush();
    assertTrue(mUnderStorageFlushed.get());
  }
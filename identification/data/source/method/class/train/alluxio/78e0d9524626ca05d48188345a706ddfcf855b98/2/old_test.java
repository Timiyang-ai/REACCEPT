  @Test
  public void cancel() throws Exception {
    mStream.cancel();

    assertTrue(mStream.isCanceled());
    assertFalse(mStream.isOpen());
    verify(mRequestObserver).cancel(any(String.class), eq(null));
  }
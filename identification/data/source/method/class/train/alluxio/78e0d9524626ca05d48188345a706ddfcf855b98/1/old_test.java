  @Test
  public void close() throws Exception {
    mStream.close();

    assertTrue(mStream.isClosed());
    assertFalse(mStream.isOpen());
    verify(mRequestObserver).onCompleted();
  }
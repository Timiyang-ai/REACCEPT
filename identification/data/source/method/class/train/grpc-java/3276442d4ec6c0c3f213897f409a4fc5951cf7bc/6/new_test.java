  @Test
  public void setStream_setAuthority() {
    final String authority = "becauseIsaidSo";
    stream.setAuthority(authority);
    stream.start(listener);
    stream.setStream(realStream);
    InOrder inOrder = inOrder(realStream);
    inOrder.verify(realStream).setAuthority(authority);
    inOrder.verify(realStream).start(any(ClientStreamListener.class));
  }
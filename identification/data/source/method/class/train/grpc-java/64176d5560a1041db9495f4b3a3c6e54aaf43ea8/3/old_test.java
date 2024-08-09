  @Test
  public void setListener_setOnlyOnce() {
    TransportState state = stream.transportState();
    state.setListener(new ServerStreamListenerBase());
    thrown.expect(IllegalStateException.class);

    state.setListener(new ServerStreamListenerBase());
  }
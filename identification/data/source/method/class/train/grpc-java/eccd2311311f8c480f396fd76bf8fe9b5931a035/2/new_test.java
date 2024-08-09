  @Test
  public void cancel_beforeStart() {
    Status status = Status.CANCELLED.withDescription("that was quick");
    stream.cancel(status);
    stream.start(listener);
    verify(listener).closed(same(status), any(Metadata.class));
  }
  @Test(expected = NullPointerException.class)
  public void addChild_nullNotAllowed() {
    Span.Builder builder = Span.newBuilder().traceId("a");
    SpanNode a = new SpanNode(builder.id("a").build());
    a.addChild(null);
  }
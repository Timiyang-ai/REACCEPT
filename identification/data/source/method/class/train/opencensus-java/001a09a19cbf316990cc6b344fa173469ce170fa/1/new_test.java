  @Test
  public void fromSpanContext_ChildLink() {
    Link link = Link.fromSpanContext(spanContext, Type.CHILD_LINKED_SPAN);
    assertThat(link.getTraceId()).isEqualTo(spanContext.getTraceId());
    assertThat(link.getSpanId()).isEqualTo(spanContext.getSpanId());
    assertThat(link.getType()).isEqualTo(Type.CHILD_LINKED_SPAN);
  }
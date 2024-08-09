  @Test public void currentSpanCustomizer_defaultsToNoop() {
    assertThat(tracer.currentSpanCustomizer())
      .isSameAs(NoopSpanCustomizer.INSTANCE);
  }
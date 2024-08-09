  @Test public void currentSpan_defaultsToNull() {
    assertThat(tracer.currentSpan()).isNull();
  }
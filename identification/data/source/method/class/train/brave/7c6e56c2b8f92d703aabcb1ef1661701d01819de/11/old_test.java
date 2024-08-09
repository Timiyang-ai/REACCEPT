  @Test public void newTrace_isRootSpan() {
    assertThat(tracer.newTrace())
      .satisfies(s -> assertThat(s.context().parentId()).isNull())
      .isInstanceOf(RealSpan.class);
  }
  @Test public void equals_sameContext() {
    Span one = tracing.tracer().toSpan(context), two = tracing.tracer().toSpan(context);

    assertThat(one)
      .isInstanceOf(RealSpan.class)
      .isNotSameAs(two)
      .isEqualTo(two);
  }
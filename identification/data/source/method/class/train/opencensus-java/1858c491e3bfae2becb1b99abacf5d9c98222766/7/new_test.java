  @Test
  public void startScopedSpan() {
    assertThat(tracer.getCurrentSpan()).isSameInstanceAs(BlankSpan.INSTANCE);
    Scope scope = spanBuilder.startScopedSpan();
    try {
      assertThat(tracer.getCurrentSpan()).isSameInstanceAs(span);
    } finally {
      scope.close();
    }
    verify(span).end(EndSpanOptions.DEFAULT);
    assertThat(tracer.getCurrentSpan()).isSameInstanceAs(BlankSpan.INSTANCE);
  }
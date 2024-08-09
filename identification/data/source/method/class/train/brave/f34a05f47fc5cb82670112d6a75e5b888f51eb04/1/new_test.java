  @Test public void setNoop_dropsDataButDoesntAffectSampling() {
    try (Tracing tracing = Tracing.newBuilder().spanReporter(spans::add).build()) {
      ScopedSpan parent = tracing.tracer().startScopedSpan("parent");

      tracing.setNoop(true);

      // a new child retains sampled from parent even in noop
      brave.Span child = tracing.tracer().newChild(parent.context());
      assertThat(child.context().sampled()).isTrue();
      assertThat(child.isNoop()).isTrue();
      child.finish();

      parent.finish();

      // a new trace is sampled from even when noop
      brave.Span root = tracing.tracer().newTrace();
      assertThat(root.context().sampled()).isTrue();
      assertThat(root.isNoop()).isTrue();
      root.finish();
    }

    assertThat(spans).isEmpty();
  }
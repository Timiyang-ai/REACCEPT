  @Test public void newChild_ensuresSampling() {
    TraceContext notYetSampled =
      tracer.newTrace().context().toBuilder().sampled(null).build();

    assertThat(tracer.newChild(notYetSampled).context().sampled())
      .isTrue();
  }
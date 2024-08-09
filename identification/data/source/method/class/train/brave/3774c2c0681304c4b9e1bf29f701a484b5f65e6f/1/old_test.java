  @Test public void startScopedSpanWithParent_getsExtraFromPropagationFactory() {
    propagationFactory = ExtraFieldPropagation.newFactory(B3Propagation.FACTORY, "service");

    TraceContext context = tracer.nextSpan().context();
    ExtraFieldPropagation.set(context, "service", "napkin");

    ScopedSpan scoped = tracer.startScopedSpanWithParent("foo", context);
    scoped.finish();

    assertThat(ExtraFieldPropagation.get(scoped.context(), "service")).isEqualTo("napkin");
  }
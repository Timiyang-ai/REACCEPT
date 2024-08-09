  @Test public void startScopedSpan_isInScope() {
    assertRealRoot(tracer.startScopedSpan("foo"));
    assertRealRoot(tracer.startScopedSpan("foo", deferDecision(), false));
  }
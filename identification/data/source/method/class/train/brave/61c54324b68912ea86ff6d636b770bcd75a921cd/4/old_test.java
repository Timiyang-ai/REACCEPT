  @Test public void nextSpan_defaultsToMakeNewTrace() {
    assertThat(tracer.nextSpan().context().parentId()).isNull();
    assertThat(tracer.nextSpan(deferDecision(), false).context().parentId()).isNull();
  }
  @Test public void build_extra() {
    TraceContextOrSamplingFlags toTest = TraceContextOrSamplingFlags.newBuilder().context(base)
      .addExtra(1L).build();
    assertThat(toTest.extra()).isEmpty();
    assertThat(toTest.context().extra()).containsExactly(1L);

    TraceIdContext idContext = TraceIdContext.newBuilder().traceId(333L).build();
    toTest = TraceContextOrSamplingFlags.newBuilder().traceIdContext(idContext)
      .addExtra(1L).build();
    assertThat(toTest.extra()).containsExactly(1L);

    toTest = TraceContextOrSamplingFlags.newBuilder()
      .samplingFlags(SamplingFlags.SAMPLED).addExtra(1L).build();
    assertThat(toTest.extra()).containsExactly(1L);
  }
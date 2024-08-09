  @Test public void sampled_true() {
    assertThat(TraceContextOrSamplingFlags.create(base).sampled(true).value.flags)
      .isEqualTo(FLAG_SAMPLED_SET | FLAG_SAMPLED);

    TraceIdContext idContext = TraceIdContext.newBuilder().traceId(333L).build();
    assertThat(TraceContextOrSamplingFlags.create(idContext).sampled(true).value.flags)
      .isEqualTo(FLAG_SAMPLED_SET | FLAG_SAMPLED);

    assertThat(TraceContextOrSamplingFlags.EMPTY.sampled(true).value.flags)
      .isEqualTo(FLAG_SAMPLED_SET | FLAG_SAMPLED);
  }
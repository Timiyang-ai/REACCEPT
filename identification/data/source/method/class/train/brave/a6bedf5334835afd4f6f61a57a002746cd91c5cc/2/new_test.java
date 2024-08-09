  @Test public void create_sampledNullDebugFalse() {
    TraceContextOrSamplingFlags flags = TraceContextOrSamplingFlags.create(null, false);

    assertThat(flags).isSameAs(TraceContextOrSamplingFlags.EMPTY);
    assertThat(flags.sampled()).isNull();
    assertThat(flags.samplingFlags()).isSameAs(SamplingFlags.EMPTY);
  }
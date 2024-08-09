  @Test
  public void fromTraceConfigProto_AlwaysSampler() {
    TraceConfig traceConfig =
        TraceConfig.newBuilder()
            .setConstantSampler(
                ConstantSampler.newBuilder().setDecision(ConstantDecision.ALWAYS_ON).build())
            .build();
    assertThat(TraceProtoUtils.fromTraceConfigProto(traceConfig, DEFAULT_PARAMS).getSampler())
        .isEqualTo(Samplers.alwaysSample());
  }
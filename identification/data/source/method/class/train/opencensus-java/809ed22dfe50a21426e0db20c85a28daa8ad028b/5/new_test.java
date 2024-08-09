  @Test
  public void toTraceConfigProto_AlwaysSampler() {
    assertThat(TraceProtoUtils.toTraceConfigProto(getTraceParams(Samplers.alwaysSample())))
        .isEqualTo(
            TraceConfig.newBuilder()
                .setConstantSampler(
                    ConstantSampler.newBuilder().setDecision(ConstantDecision.ALWAYS_ON).build())
                .build());
  }
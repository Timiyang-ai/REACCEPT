public static TraceConfig toTraceConfigProto(TraceParams traceParams) {
    TraceConfig.Builder traceConfigProtoBuilder = TraceConfig.newBuilder();
    Sampler librarySampler = traceParams.getSampler();

    if (Samplers.alwaysSample().equals(librarySampler)) {
      traceConfigProtoBuilder.setConstantSampler(
          ConstantSampler.newBuilder().setDecision(true).build());
    } else if (Samplers.neverSample().equals(librarySampler)) {
      traceConfigProtoBuilder.setConstantSampler(
          ConstantSampler.newBuilder().setDecision(false).build());
    } else {
      // TODO: consider exposing the sampling probability of ProbabilitySampler.
      double samplingProbability = parseSamplingProbability(librarySampler);
      traceConfigProtoBuilder.setProbabilitySampler(
          ProbabilitySampler.newBuilder().setSamplingProbability(samplingProbability).build());
    } // TODO: add support for RateLimitingSampler.

    return traceConfigProtoBuilder.build();
  }
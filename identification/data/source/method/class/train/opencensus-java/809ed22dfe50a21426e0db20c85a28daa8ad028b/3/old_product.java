static TraceParams fromTraceConfigProto(
      TraceConfig traceConfigProto, TraceParams currentTraceParams) {
    TraceParams.Builder builder = currentTraceParams.toBuilder();
    if (traceConfigProto.hasConstantSampler()) {
      ConstantSampler constantSampler = traceConfigProto.getConstantSampler();
      if (Boolean.TRUE.equals(constantSampler.getDecision())) {
        builder.setSampler(Samplers.alwaysSample());
      } else {
        builder.setSampler(Samplers.neverSample());
      }
    } else if (traceConfigProto.hasProbabilitySampler()) {
      builder.setSampler(
          Samplers.probabilitySampler(
              traceConfigProto.getProbabilitySampler().getSamplingProbability()));
    } // TODO: add support for RateLimitingSampler.
    return builder.build();
  }
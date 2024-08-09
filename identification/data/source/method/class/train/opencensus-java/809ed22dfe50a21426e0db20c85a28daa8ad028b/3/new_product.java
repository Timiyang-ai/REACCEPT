static TraceParams fromTraceConfigProto(
      TraceConfig traceConfigProto, TraceParams currentTraceParams) {
    TraceParams.Builder builder = currentTraceParams.toBuilder();
    if (traceConfigProto.hasConstantSampler()) {
      ConstantSampler constantSampler = traceConfigProto.getConstantSampler();
      ConstantDecision decision = constantSampler.getDecision();
      if (ConstantDecision.ALWAYS_ON.equals(decision)) {
        builder.setSampler(Samplers.alwaysSample());
      } else if (ConstantDecision.ALWAYS_OFF.equals(decision)) {
        builder.setSampler(Samplers.neverSample());
      } // else if (ConstantDecision.ALWAYS_PARENT.equals(decision)) {
      // For ALWAYS_PARENT, don't need to update configs since in Java by default parent sampling
      // decision always takes precedence.
      // }
    } else if (traceConfigProto.hasProbabilitySampler()) {
      builder.setSampler(
          Samplers.probabilitySampler(
              traceConfigProto.getProbabilitySampler().getSamplingProbability()));
    } // TODO: add support for RateLimitingSampler.
    return builder.build();
  }
public Tracer withSampler(Sampler sampler) {
    if (sampler == null) throw new NullPointerException("sampler == null");
    return new Tracer(
        clock,
        propagationFactory,
        reporter,
        recorder,
        sampler,
        errorParser,
        currentTraceContext,
        traceId128Bit,
        supportsJoin,
        noop
    );
  }
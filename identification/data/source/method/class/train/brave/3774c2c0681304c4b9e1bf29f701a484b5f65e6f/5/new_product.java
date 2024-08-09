public Tracer withSampler(Sampler sampler) {
    if (sampler == null) throw new NullPointerException("sampler == null");
    return new Tracer(
        clock,
        propagationFactory,
        spanReporter,
        pendingSpans,
        sampler,
        errorParser,
        currentTraceContext,
        traceId128Bit,
        supportsJoin,
        noop
    );
  }
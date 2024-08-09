public Tracer withSampler(Sampler sampler) {
    if (sampler == null) throw new NullPointerException("sampler == null");
    return new Tracer(
        clock,
        propagationFactory,
        firehoseHandler,
        pendingSpans,
        sampler,
        currentTraceContext,
        traceId128Bit,
        supportsJoin,
        alwaysSampleLocal,
        noop
    );
  }
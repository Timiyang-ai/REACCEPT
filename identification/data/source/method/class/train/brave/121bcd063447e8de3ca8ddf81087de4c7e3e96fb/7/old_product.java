public Span nextSpan(Req request) {
    TraceContext parent = currentTraceContext.get();
    if (parent != null) return tracer.newChild(parent); // inherit the sampling decision

    // If there was no parent, we are making a new trace. Try to sample the request.
    Boolean sampled = sampler.trySample(adapter, request);
    if (sampled == null) return tracer.newTrace(); // defer sampling decision to trace ID
    return tracer.newTrace(sampled ? SamplingFlags.SAMPLED : SamplingFlags.NOT_SAMPLED);
  }
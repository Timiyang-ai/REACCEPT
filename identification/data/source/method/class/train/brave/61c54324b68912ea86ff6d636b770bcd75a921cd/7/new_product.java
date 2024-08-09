public Span nextSpan(TraceContextOrSamplingFlags extracted) {
    TraceContext parent = extracted.context();
    if (extracted.samplingFlags() != null) {
      TraceContext implicitParent = currentTraceContext.get();
      if (implicitParent == null) {
        return toSpan(newRootContext(extracted.samplingFlags(), extracted.extra()));
      }
      // fall through, with an implicit parent, not an extracted one
      parent = appendExtra(implicitParent, extracted.extra());
    }
    long nextId = nextId();
    if (parent != null) {
      Boolean sampled = parent.sampled();
      if (sampled == null) sampled = sampler.isSampled(parent.traceId());
      return toSpan(parent.toBuilder() // copies "extra" from the parent
          .spanId(nextId)
          .parentId(parent.spanId())
          .sampled(sampled)
          .build());
    }
    TraceIdContext traceIdContext = extracted.traceIdContext();
    if (extracted.traceIdContext() != null) {
      Boolean sampled = traceIdContext.sampled();
      if (sampled == null) sampled = sampler.isSampled(traceIdContext.traceId());
      return toSpan(TraceContext.newBuilder()
          .sampled(sampled)
          .debug(traceIdContext.debug())
          .traceIdHigh(traceIdContext.traceIdHigh()).traceId(traceIdContext.traceId())
          .spanId(nextId)
          .extra(extracted.extra()).build());
    }
    // TraceContextOrSamplingFlags is a union of 3 types, we've checked all three
    throw new AssertionError("should not reach here");
  }
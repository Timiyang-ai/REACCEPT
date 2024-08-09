public Span nextSpan(TraceContextOrSamplingFlags extracted) {
    if (extracted == null) throw new NullPointerException("extracted == null");
    TraceContext context = extracted.context();
    if (context != null) return newChild(context);

    TraceIdContext traceIdContext = extracted.traceIdContext();
    if (traceIdContext != null) {
      return _toSpan(nextContext(
          InternalPropagation.instance.flags(extracted.traceIdContext()),
          traceIdContext.traceIdHigh(),
          traceIdContext.traceId(),
          0L,
          extracted.extra()
      ));
    }

    SamplingFlags samplingFlags = extracted.samplingFlags();
    List<Object> extra = extracted.extra();

    TraceContext implicitParent = currentTraceContext.get();
    int flags;
    long traceIdHigh = 0L, traceId = 0L, spanId = 0L;
    if (implicitParent != null) {
      // At this point, we didn't extract trace IDs, but do have a trace in progress. Since typical
      // trace sampling is up front, we retain the decision from the parent.
      flags = InternalPropagation.instance.flags(implicitParent);
      traceIdHigh = implicitParent.traceIdHigh();
      traceId = implicitParent.traceId();
      spanId = implicitParent.spanId();
      extra = concatImmutableLists(extra, implicitParent.extra());
    } else {
      flags = InternalPropagation.instance.flags(samplingFlags);
    }
    return _toSpan(nextContext(flags, traceIdHigh, traceId, spanId, extra));
  }
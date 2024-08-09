@Deprecated
  public TraceContextOrSamplingFlags sampled(@Nullable Boolean sampled) {
    if (sampled != null) return sampled(sampled.booleanValue());
    if (value.flags == 0) return this; // save effort if no change

    switch (type) {
      case 1:
        // use bitwise as trace context can have other flags like shared
        int flags = value.flags & ~(FLAG_SAMPLED_SET | FLAG_SAMPLED);
        TraceContext traceContext = contextWithFlags((TraceContext) value, flags);
        return new TraceContextOrSamplingFlags(type, traceContext, extra);
      case 2:
        return new TraceContextOrSamplingFlags(type, idContextWithFlags(0), extra);
      case 3:
        if (extra.isEmpty()) return EMPTY;
        return new TraceContextOrSamplingFlags(type, SamplingFlags.EMPTY, extra);
    }
    throw new AssertionError("programming error");
  }
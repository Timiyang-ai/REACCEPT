@Deprecated
  public static TraceContextOrSamplingFlags create(TraceContext.Builder builder) {
    if (builder == null) throw new NullPointerException("builder == null");
    if (builder.traceId != 0L && builder.spanId != 0L) {
      return create(builder.build());
    } else { // no trace IDs, but it might have sampling flags
      SamplingFlags flags = new SamplingFlags.Builder()
          .sampled(SamplingFlags.sampled(builder.flags))
          .debug(SamplingFlags.debug(builder.flags)).build();
      return create(flags);
    }
  }
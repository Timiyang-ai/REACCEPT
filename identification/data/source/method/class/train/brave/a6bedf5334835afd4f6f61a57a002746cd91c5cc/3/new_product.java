@Deprecated
  public static TraceContextOrSamplingFlags create(TraceContext.Builder builder) {
    if (builder == null) throw new NullPointerException("builder == null");
    try {
      return create(builder.build());
    } catch (IllegalStateException e) { // no trace IDs, but it might have sampling flags
      SamplingFlags flags = new SamplingFlags.Builder()
          .sampled(builder.sampled())
          .debug(builder.debug()).build();
      return create(flags);
    }
  }
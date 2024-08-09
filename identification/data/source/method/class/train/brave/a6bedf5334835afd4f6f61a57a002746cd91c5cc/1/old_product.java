public static TraceContextOrSamplingFlags create(TraceContext.Builder builder) {
    if (builder == null) throw new NullPointerException("builder == null");
    try {
      return new AutoValue_TraceContextOrSamplingFlags(builder.build(), null);
    } catch (IllegalStateException e) { // no trace IDs, but it might have sampling flags
      SamplingFlags flags = new SamplingFlags.Builder()
          .sampled(builder.sampled())
          .debug(builder.debug()).build();
      return new AutoValue_TraceContextOrSamplingFlags(null, flags);
    }
  }
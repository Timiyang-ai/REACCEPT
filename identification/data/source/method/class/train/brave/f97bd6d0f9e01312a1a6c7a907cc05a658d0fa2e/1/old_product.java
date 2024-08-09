public TraceContextOrSamplingFlags sampled(@Nullable Boolean sampled) {
    switch (type) {
      case 1:
        return new TraceContextOrSamplingFlags(type, ((TraceContext) value).toBuilder()
            .sampled(sampled).build(), extra);
      case 2:
        return new TraceContextOrSamplingFlags(type, ((TraceIdContext) value).toBuilder()
            .sampled(sampled).build(), extra);
      case 3:
        return new TraceContextOrSamplingFlags(type, new SamplingFlags.Builder()
            .sampled(sampled).debug(value.debug()).build(), extra);
    }
    throw new AssertionError("programming error");
  }
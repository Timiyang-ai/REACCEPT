public final TraceContextOrSamplingFlags build() {
      if (!extra.isEmpty() && type == 1) { // move extra to the trace context
        TraceContext context = (TraceContext) value;
        if (context.extra().isEmpty()) {
          context = contextWithExtra(context, ensureImmutable(extra));
        } else {
          context = contextWithExtra(context, concatImmutableLists(context.extra(), extra));
        }
        return new TraceContextOrSamplingFlags(type, context, emptyList());
      }
      // make sure the extra data is immutable and unmodifiable
      return new TraceContextOrSamplingFlags(type, value, ensureImmutable(extra));
    }
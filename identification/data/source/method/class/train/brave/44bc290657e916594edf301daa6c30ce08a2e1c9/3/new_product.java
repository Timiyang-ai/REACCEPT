public final TraceContextOrSamplingFlags build() {
      if (!extra.isEmpty() && type == 1) { // move extra to the trace context
        TraceContext context = (TraceContext) value;
        if (context.extra().isEmpty()) {
          context = context.toBuilder().extra(extra).build();
          return new TraceContextOrSamplingFlags(type, context, Collections.emptyList());
        }
        ArrayList<Object> copy = new ArrayList<>(extra);
        copy.addAll(context.extra());
        context = context.toBuilder().extra(copy).build();
        return new TraceContextOrSamplingFlags(type, context, Collections.emptyList());
      }
      // make sure the extra data is immutable and unmodifiable
      return new TraceContextOrSamplingFlags(type, value, extra);
    }
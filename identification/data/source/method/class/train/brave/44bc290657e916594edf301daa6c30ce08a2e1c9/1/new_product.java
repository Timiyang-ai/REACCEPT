public final TraceContextOrSamplingFlags build() {
      final TraceContextOrSamplingFlags result;
      if (!extra.isEmpty() && type == 1) { // move extra to the trace context
        TraceContext context = (TraceContext) value;
        if (context.extra().isEmpty()) {
          context = InternalPropagation.instance.withExtra(context, ensureImmutable(extra));
        } else {
          context = InternalPropagation.instance.withExtra(context,
              concatImmutableLists(context.extra(), extra));
        }
        result = new TraceContextOrSamplingFlags(type, context, emptyList());
      } else {
        // make sure the extra data is immutable and unmodifiable
        result = new TraceContextOrSamplingFlags(type, value, ensureImmutable(extra));
      }

      if (!sampledLocal) return result; // save effort if no change
      return result.withFlags(value.flags | FLAG_SAMPLED_LOCAL);
    }
public static Link fromSpanContext(SpanContext context, Type type) {
    return new AutoValue_Link(context.getTraceId(), context.getSpanId(), type);
  }
public static Link fromSpanContext(SpanContext context, Type type) {
    return new Link(context.getTraceId(), context.getSpanId(), type);
  }
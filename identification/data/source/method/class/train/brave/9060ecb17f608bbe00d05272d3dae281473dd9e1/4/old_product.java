public Req handleSend(Req request, Span span) {
    if (span.isNoop()) return request;

    // all of the parsing here occur before a timestamp is recorded on the span
    span.kind(Span.Kind.CLIENT).name(parser.spanName(adapter, request));
    parser.requestTags(adapter, request, span);
    span.start();
    return request;
  }
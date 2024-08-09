public void handleReceive(Req request, Span span) {
    if (span.isNoop()) return;

    // all of the parsing here occur before a timestamp is recorded on the span
    span.kind(Span.Kind.SERVER).name(parser.spanName(adapter, request));
    parser.requestTags(adapter, request, span);
    span.start();
  }
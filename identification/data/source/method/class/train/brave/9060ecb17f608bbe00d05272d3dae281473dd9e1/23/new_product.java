@Deprecated public Span handleReceive(Extractor<Req> extractor, Req request) {
    return handleReceive(extractor, request, request);
  }
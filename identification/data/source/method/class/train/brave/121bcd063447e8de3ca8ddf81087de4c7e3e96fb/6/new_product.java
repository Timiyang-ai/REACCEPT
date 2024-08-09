@Deprecated public Span nextSpan(Req request) {
    // nextSpan can be called independently when interceptors control lifecycle directly. In these
    // cases, it is possible to have HttpClientRequest as an argument.
    HttpClientRequest clientRequest;
    if (request instanceof HttpClientRequest) {
      clientRequest = (HttpClientRequest) request;
    } else {
      clientRequest = new HttpClientRequest.FromHttpAdapter<>(adapter, request);
    }
    return nextClientSpan(clientRequest);
  }
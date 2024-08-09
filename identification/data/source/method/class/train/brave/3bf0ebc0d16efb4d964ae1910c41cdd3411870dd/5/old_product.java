public <Resp> void response(HttpAdapter<?, Resp> adapter, @Nullable Resp res,
      @Nullable Throwable error, SpanCustomizer customizer) {
    Integer httpStatus = res != null ? adapter.statusCode(res) : null;
    if (httpStatus != null && (httpStatus < 200 || httpStatus > 299)) {
      customizer.tag("http.status_code", String.valueOf(httpStatus));
    }
    error(httpStatus, error, customizer);
  }
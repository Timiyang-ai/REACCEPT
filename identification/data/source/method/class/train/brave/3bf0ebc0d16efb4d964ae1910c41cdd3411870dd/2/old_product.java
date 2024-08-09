public <Resp> void response(HttpAdapter<?, Resp> adapter, @Nullable Resp res,
      @Nullable Throwable error, SpanCustomizer customizer) {
    Integer httpStatus = res != null ? adapter.statusCode(res) : null;
    if (httpStatus != null && (httpStatus < 200 || httpStatus > 299)) {
      customizer.tag(TraceKeys.HTTP_STATUS_CODE, String.valueOf(httpStatus));
    }
    error(httpStatus, error, customizer);
  }
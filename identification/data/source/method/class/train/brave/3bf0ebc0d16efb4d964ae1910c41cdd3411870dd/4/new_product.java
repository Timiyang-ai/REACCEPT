public <Resp> void response(HttpAdapter<?, Resp> adapter, @Nullable Resp res,
      @Nullable Throwable error, SpanCustomizer customizer) {
    int statusCode = 0;
    if (res != null) {
      statusCode = adapter.statusCodeAsInt(res);
      String nameFromRoute = spanNameFromRoute(adapter, res, statusCode);
      if (nameFromRoute != null) customizer.name(nameFromRoute);
      if (statusCode != 0 && (statusCode < 200 || statusCode > 299)) {
        customizer.tag("http.status_code", String.valueOf(statusCode));
      }
    }
    error(statusCode, error, customizer);
  }
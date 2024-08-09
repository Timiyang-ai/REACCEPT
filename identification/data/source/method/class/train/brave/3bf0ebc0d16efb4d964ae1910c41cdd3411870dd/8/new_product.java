public <Resp> void response(HttpAdapter<?, Resp> adapter, @Nullable Resp res,
      @Nullable Throwable error, SpanCustomizer customizer) {
    int statusCode = 0;
    if (res != null) {
      statusCode = adapter.statusCodeAsInt(res);
      String nameFromRoute = spanNameFromRoute(adapter, res, statusCode);
      if (nameFromRoute != null) customizer.name(nameFromRoute);
      String maybeStatus = maybeStatusAsString(statusCode, 299);
      if (maybeStatus != null) customizer.tag("http.status_code", maybeStatus);
    }
    error(statusCode, error, customizer);
  }
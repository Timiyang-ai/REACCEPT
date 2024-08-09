public void response(Resp response, @Nullable Throwable error, SpanCustomizer customizer) {
    String errorMessage = response.errorMessage();
    if (errorMessage != null) customizer.tag("rpc.error_message", errorMessage);
    if (errorMessage != null || error != null) error(errorMessage, error, customizer);
  }
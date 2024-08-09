public void error(Throwable error, SpanCustomizer customizer) {
    String message = error.getMessage();
    if (message == null) message = error.getClass().getSimpleName();
    customizer.tag("error", message);
  }
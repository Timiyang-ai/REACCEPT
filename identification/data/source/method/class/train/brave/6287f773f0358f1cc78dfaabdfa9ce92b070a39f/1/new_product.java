protected void error(Throwable error, Object span) {
    String message = error.getMessage();
    if (message == null) message = error.getClass().getSimpleName();
    tag(span, "error", message);
  }
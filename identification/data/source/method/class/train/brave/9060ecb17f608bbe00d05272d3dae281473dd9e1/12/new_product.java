public void handleReceive(@Nullable Resp response, @Nullable Throwable error, Span span) {
    handleFinish(response, error, span);
  }
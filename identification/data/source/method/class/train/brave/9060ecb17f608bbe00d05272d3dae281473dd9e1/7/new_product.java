public void handleSend(@Nullable Resp response, @Nullable Throwable error, Span span) {
    if (response instanceof HttpServerResponse) {
      HttpServerResponse.Adapter adapter =
        new HttpServerResponse.Adapter((HttpServerResponse) response);
      handleFinish(adapter, adapter.unwrapped, error, span);
    } else {
      handleFinish(adapter, response, error, span);
    }
  }
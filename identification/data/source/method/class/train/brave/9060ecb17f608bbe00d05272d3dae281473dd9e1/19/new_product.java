public void handleReceive(@Nullable Resp response, @Nullable Throwable error, Span span) {
    if (response instanceof HttpClientResponse) {
      HttpClientResponse.Adapter adapter =
        new HttpClientResponse.Adapter((HttpClientResponse) response);
      handleFinish(adapter, adapter.unwrapped, error, span);
    } else {
      handleFinish(adapter, response, error, span);
    }
  }
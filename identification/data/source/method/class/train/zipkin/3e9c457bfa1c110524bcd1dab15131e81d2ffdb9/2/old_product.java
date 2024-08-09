public void accept(List<Span> spans, Callback<Void> callback, Executor executor) {
    if (spans.isEmpty()) {
      callback.onSuccess(null);
      return;
    }

    try {
      metrics.incrementSpans(spans.size());

      List<Span> handledSpans = handle(spans);
      if (handledSpans.isEmpty()) {
        callback.onSuccess(null);
        return;
      }

      // In order to ensure callers are not blocked, we swap callbacks when we get to the storage
      // phase of this process. Here, we create a callback whose sole purpose is classifying later
      // errors on this bundle of spans in the same log category. This allows people to only turn on
      // debug logging in one place.
      executor.execute(new StoreSpans(handledSpans));
      callback.onSuccess(null);
    } catch (Throwable unexpected) { // ensure if a future is supplied we always set value or error
      Call.propagateIfFatal(unexpected);
      callback.onError(unexpected);
    }
  }
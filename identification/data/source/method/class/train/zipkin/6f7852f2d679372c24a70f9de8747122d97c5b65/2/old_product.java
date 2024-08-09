public void acceptSpans(List<byte[]> serializedSpans, SpanDecoder decoder,
    Callback<Void> callback) {
    List<Span> spans = new ArrayList<>(serializedSpans.size());
    try {
      int bytesRead = 0;
      for (byte[] serializedSpan : serializedSpans) {
        bytesRead += serializedSpan.length;
        spans.add(decoder.readSpan(serializedSpan));
      }
      metrics.incrementBytes(bytesRead);
    } catch (RuntimeException e) {
      callback.onError(errorReading(e));
      return;
    }
    accept(spans, callback);
  }
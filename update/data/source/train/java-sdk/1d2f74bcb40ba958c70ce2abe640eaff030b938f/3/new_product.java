public void recognize(final InputStream stream, final RecognizeOptions options, RecognizeCallback delegate) {
    createConnection(options).enqueue(new SpeechToTextWebSocketListener(stream, options, delegate));
  }
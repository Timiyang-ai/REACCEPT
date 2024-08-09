@VisibleForTesting
  void skipToNextPage(ExtractorInput input) throws IOException, InterruptedException {
    if (!skipToNextPage(input, payloadEndPosition)) {
      // Not found until eof.
      throw new EOFException();
    }
  }
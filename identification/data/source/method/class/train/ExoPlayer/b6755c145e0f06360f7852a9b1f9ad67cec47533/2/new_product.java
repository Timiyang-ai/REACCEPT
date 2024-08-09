void skipToNextPage(ExtractorInput input) throws IOException, InterruptedException {
    if (!skipToNextPage(input, endPosition)) {
      // Not found until eof.
      throw new EOFException();
    }
  }
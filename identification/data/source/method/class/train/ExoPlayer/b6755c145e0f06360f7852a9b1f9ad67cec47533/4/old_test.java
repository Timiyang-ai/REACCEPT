  private static void skipToNextPage(ExtractorInput extractorInput)
      throws IOException, InterruptedException {
    DefaultOggSeeker oggSeeker =
        new DefaultOggSeeker(
            /* streamReader= */ new FlacReader(),
            /* payloadStartPosition= */ 0,
            /* payloadEndPosition= */ extractorInput.getLength(),
            /* firstPayloadPageSize= */ 1,
            /* firstPayloadPageGranulePosition= */ 2,
            /* firstPayloadPageIsLastPage= */ false);
    while (true) {
      try {
        oggSeeker.skipToNextPage(extractorInput);
        break;
      } catch (FakeExtractorInput.SimulatedIOException e) {
        /* ignored */
      }
    }
  }
  private static VorbisSetup readSetupHeaders(VorbisReader reader, ExtractorInput input)
      throws IOException, InterruptedException {
    OggPacket oggPacket = new OggPacket();
    while (true) {
      try {
        if (!oggPacket.populate(input)) {
          fail();
        }
        VorbisSetup vorbisSetup = reader.readSetupHeaders(oggPacket.getPayload());
        if (vorbisSetup != null) {
          return vorbisSetup;
        }
      } catch (SimulatedIOException e) {
        // Ignore.
      }
    }
  }
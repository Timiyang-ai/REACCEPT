@Test
  public void testSynthesize() throws IOException {
    final String text = "This is an integration test";
    final File audio = File.createTempFile("tts-audio", "wav");

    synthesize(text, audio);
  }
@Test
  public void testSynthesize() throws IOException {
    String text = "This is an integration test";
    InputStream result = service.synthesize(text, Voice.EN_LISA, AudioFormat.WAV).execute();
    writeInputStreamToFile(result, File.createTempFile("tts-audio", "wav"));
  }
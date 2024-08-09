@Test
  public void testSynthesize() throws IOException {
    String text = "This is an integration test; 1,2 !, @, #, $, %, ^, 20.";
    InputStream result = service.synthesize(text, Voice.EN_LISA, AudioFormat.WAV).execute();
    writeInputStreamToFile(result, File.createTempFile("tts-audio", "wav"));
  }
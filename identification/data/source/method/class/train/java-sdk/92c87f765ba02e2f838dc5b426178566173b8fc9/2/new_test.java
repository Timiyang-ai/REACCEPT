@Test
  public void testRecognize() throws URISyntaxException, InterruptedException, FileNotFoundException {
    server.enqueue(new MockResponse()
        .addHeader(CONTENT_TYPE, HttpMediaType.APPLICATION_JSON)
        .setBody(GSON.toJson(recognitionResults)));

    RecognizeOptions recognizeOptions = new RecognizeOptions.Builder()
        .audio(SAMPLE_WAV)
        .contentType(RecognizeOptions.ContentType.AUDIO_WAV)
        .build();
    final SpeechRecognitionResults result = service.recognize(recognizeOptions).execute();
    final RecordedRequest request = server.takeRequest();

    assertNotNull(result);
    assertEquals(result, recognitionResults);
    assertEquals("POST", request.getMethod());
    assertEquals(PATH_RECOGNIZE, request.getPath());
    assertEquals(HttpMediaType.AUDIO_WAV, request.getHeader(CONTENT_TYPE));
  }
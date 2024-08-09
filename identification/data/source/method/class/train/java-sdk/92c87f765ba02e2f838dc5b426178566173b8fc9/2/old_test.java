@Test
  public void testRecognize() throws URISyntaxException, InterruptedException, FileNotFoundException {

    final SpeechRecognitionResults speechResults = new SpeechRecognitionResults();
    speechResults.setResultIndex(0);
    final SpeechRecognitionResult transcript = new SpeechRecognitionResult();
    transcript.setFinalResults(true);
    final SpeechRecognitionAlternative speechAlternative = new SpeechRecognitionAlternative();
    speechAlternative.setTranscript("thunderstorms could produce large hail isolated tornadoes and heavy rain");

    final List<SpeechRecognitionAlternative> speechAlternatives = ImmutableList.of(speechAlternative);
    transcript.setAlternatives(speechAlternatives);

    final List<SpeechRecognitionResult> transcripts = ImmutableList.of(transcript);
    speechResults.setResults(transcripts);

    server.enqueue(
        new MockResponse().addHeader(CONTENT_TYPE, HttpMediaType.APPLICATION_JSON).setBody(GSON.toJson(speechResults)));

    RecognizeOptions recognizeOptions = new RecognizeOptions.Builder()
        .audio(SAMPLE_WAV)
        .contentType(RecognizeOptions.ContentType.AUDIO_WAV)
        .build();
    final SpeechRecognitionResults result = service.recognize(recognizeOptions).execute();
    final RecordedRequest request = server.takeRequest();

    assertNotNull(result);
    assertEquals(result, speechResults);
    assertEquals("POST", request.getMethod());
    assertEquals(PATH_RECOGNIZE, request.getPath());
    assertEquals(HttpMediaType.AUDIO_WAV, request.getHeader(CONTENT_TYPE));
  }
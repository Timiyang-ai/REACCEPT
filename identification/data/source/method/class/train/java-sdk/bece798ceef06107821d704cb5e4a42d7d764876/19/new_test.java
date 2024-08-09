@Test
  public void testRecognize() throws URISyntaxException {

    final SpeechResults speechResults = new SpeechResults();
    speechResults.setResultIndex(0);
    final Transcript transcript = new Transcript();
    transcript.setFinal(true);
    final SpeechAlternative speechAlternative = new SpeechAlternative();
    speechAlternative
        .setTranscript("thunderstorms could produce large hail isolated tornadoes and heavy rain");
    final List<SpeechAlternative> speechAlternatives = new ArrayList<SpeechAlternative>();
    speechAlternatives.add(speechAlternative);

    transcript.setAlternatives(speechAlternatives);
    final List<Transcript> transcripts = new ArrayList<Transcript>();
    transcripts.add(transcript);
    speechResults.setResults(transcripts);

    final File audio = new File("src/test/resources/sample1.wav");

    mockServer.when(
        request().withMethod("POST").withPath(RECOGNIZE_PATH)
            .withHeaders(new Header(HttpHeaders.Names.CONTENT_TYPE, "audio/l16; rate=44100")))

    .respond(
        response().withHeaders(
            new Header(HttpHeaders.Names.CONTENT_TYPE, HttpMediaType.APPLICATION_JSON)).withBody(
            GsonSingleton.getGson().toJson(speechResults)));
    final SpeechResults result = service.recognize(audio, "audio/l16; rate=44100");
    Assert.assertNotNull(result);
    Assert.assertEquals(result, speechResults);
  }
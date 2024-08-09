@Test
  public void testAddWords() throws InterruptedException {
    final CustomVoiceModel model = instantiateVoiceModel();
    final List<CustomTranslation> expected = instantiateWords();

    server.enqueue(new MockResponse().setResponseCode(201));
    service.addWords(model, expected.toArray(new CustomTranslation[] { })).execute();
    RecordedRequest request = server.takeRequest();

    assertEquals(String.format(WORDS_PATH, model.getId()), request.getPath());
    assertEquals("POST", request.getMethod());

    server.enqueue(new MockResponse().setResponseCode(201));
    service.addWords(model, expected.get(0)).execute();
    request = server.takeRequest();

    assertEquals(String.format(WORDS_PATH, model.getId()), request.getPath());
    assertEquals("POST", request.getMethod());
  }
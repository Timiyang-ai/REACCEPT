@Test
  public void testAddWords() throws InterruptedException {
    final VoiceModel model = instantiateVoiceModel();
    final List<Word> expected = instantiateWords();

    server.enqueue(new MockResponse().setResponseCode(201));
    AddWordsOptions addOptions = new AddWordsOptions.Builder()
        .customizationId(model.getCustomizationId())
        .words(expected)
        .build();
    service.addWords(addOptions).execute();
    RecordedRequest request = server.takeRequest();

    assertEquals(String.format(WORDS_PATH, model.getCustomizationId()), request.getPath());
    assertEquals("POST", request.getMethod());
  }
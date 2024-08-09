@Test
  public void testAddWords() throws InterruptedException, FileNotFoundException {
    String id = "foo";
    Word newWord = loadFixture("src/test/resources/speech_to_text/word.json", Word.class);
    Map<String, Object> wordsAsMap = new HashMap<String, Object>();
    wordsAsMap.put("words", new Word[] { newWord });
    server.enqueue(new MockResponse().addHeader(CONTENT_TYPE, HttpMediaType.APPLICATION_JSON).setBody("{}"));

    CustomWord word = new CustomWord();
    word.setWord(newWord.getWord());
    word.setDisplayAs(newWord.getDisplayAs());
    word.setSoundsLike(newWord.getSoundsLike());

    AddWordsOptions addOptions = new AddWordsOptions.Builder()
        .customizationId(id)
        .addWords(word)
        .build();
    service.addWords(addOptions).execute();
    final RecordedRequest request = server.takeRequest();

    assertEquals("POST", request.getMethod());
    assertEquals(String.format(PATH_WORDS, id), request.getPath());
    assertEquals(GSON.toJson(wordsAsMap), request.getBody().readUtf8());
  }
@Test
  public void testAddWords() throws InterruptedException, FileNotFoundException {
    String id = "foo";
    Word newWord = loadFixture("src/test/resources/speech_to_text/word.json", Word.class);
    Map<String, Object> wordsAsMap = new HashMap<String, Object>();
    wordsAsMap.put("words", new Word[] {newWord});
    server.enqueue(new MockResponse().addHeader(CONTENT_TYPE, HttpMediaType.APPLICATION_JSON).setBody("{}"));

    AddWordsOptions addOptions = new AddWordsOptions.Builder()
        .customizationId(id)
        .customWords(new CustomWords.Builder()
            .addWords(new CustomWord.Builder()
                .word(newWord.getWord())
                .displayAs(newWord.getDisplayAs())
                .soundsLike(newWord.getSoundsLike())
                .build())
            .build())
        .build();
    service.addWords(addOptions).execute();
    final RecordedRequest request = server.takeRequest();

    assertEquals("POST", request.getMethod());
    assertEquals(String.format(PATH_WORDS, id), request.getPath());
    assertEquals(GSON.toJson(wordsAsMap), request.getBody().readUtf8());
  }
@Test
  public void testAddWord() throws InterruptedException, FileNotFoundException {
    String id = "foo";
    Word newWord = loadFixture("src/test/resources/speech_to_text/word.json", Word.class);
    server.enqueue(new MockResponse().addHeader(CONTENT_TYPE, HttpMediaType.APPLICATION_JSON).setBody("{}"));

    service.addWord(id, newWord).execute();
    final RecordedRequest request = server.takeRequest();

    assertEquals("PUT", request.getMethod());
    assertEquals(String.format(PATH_WORD, id, newWord.getWord()), request.getPath());
    assertEquals(GSON.toJson(newWord), request.getBody().readUtf8());
  }
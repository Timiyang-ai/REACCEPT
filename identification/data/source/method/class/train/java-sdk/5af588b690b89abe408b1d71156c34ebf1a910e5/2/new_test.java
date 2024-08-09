@Test
  public void testGetWord() throws InterruptedException, FileNotFoundException {
    String id = "foo";
    String wordName = "bar";

    String wordAsStr = getStringFromInputStream(new FileInputStream("src/test/resources/speech_to_text/word.json"));
    JsonObject word = new JsonParser().parse(wordAsStr).getAsJsonObject();

    server.enqueue(new MockResponse().addHeader(CONTENT_TYPE, HttpMediaType.APPLICATION_JSON).setBody(wordAsStr));

    GetWordOptions getOptions = new GetWordOptions.Builder()
        .customizationId(id)
        .wordName(wordName)
        .build();
    Word result = service.getWord(getOptions).execute();
    final RecordedRequest request = server.takeRequest();

    assertEquals("GET", request.getMethod());
    assertEquals(String.format(PATH_WORD, id, wordName), request.getPath());
    assertEquals(word, GSON.toJsonTree(result));
  }
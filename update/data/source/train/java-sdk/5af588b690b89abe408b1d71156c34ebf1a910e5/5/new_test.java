@Test
  public void testDeleteWord() throws InterruptedException {
    String id = "foo";
    String wordName = "bar";

    server.enqueue(new MockResponse().addHeader(CONTENT_TYPE, HttpMediaType.APPLICATION_JSON).setBody("{}"));

    DeleteWordOptions deleteOptions = new DeleteWordOptions.Builder()
        .customizationId(id)
        .wordName(wordName)
        .build();
    service.deleteWord(deleteOptions).execute();
    final RecordedRequest request = server.takeRequest();

    assertEquals("DELETE", request.getMethod());
    assertEquals(String.format(PATH_WORD, id, wordName), request.getPath());
  }
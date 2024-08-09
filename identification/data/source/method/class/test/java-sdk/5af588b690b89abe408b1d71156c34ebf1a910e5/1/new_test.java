@Test
  public void testDeleteCorpus() throws InterruptedException, FileNotFoundException {
    String id = "foo";
    String corpus = "cName";

    server.enqueue(new MockResponse().addHeader(CONTENT_TYPE, HttpMediaType.APPLICATION_JSON).setBody("{}"));

    DeleteCorpusOptions deleteOptions = new DeleteCorpusOptions.Builder()
        .customizationId(id)
        .corpusName(corpus)
        .build();
    service.deleteCorpus(deleteOptions).execute();
    final RecordedRequest request = server.takeRequest();

    assertEquals("DELETE", request.getMethod());
    assertEquals(String.format(PATH_CORPUS, id, corpus), request.getPath());
  }
@Test
  public void testGetCorpus() throws InterruptedException, FileNotFoundException {
    String id = "foo";
    String corpus = "cName";

    server.enqueue(new MockResponse().addHeader(CONTENT_TYPE, HttpMediaType.APPLICATION_JSON).setBody("{}"));

    service.getCorpus(id, corpus).execute();
    final RecordedRequest request = server.takeRequest();

    assertEquals("GET", request.getMethod());
    assertEquals(String.format(PATH_CORPUS, id, corpus), request.getPath());
  }
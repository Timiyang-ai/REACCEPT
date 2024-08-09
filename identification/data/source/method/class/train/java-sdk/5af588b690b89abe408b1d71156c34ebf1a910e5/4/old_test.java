@Test
  public void testAddCorpus() throws InterruptedException, FileNotFoundException {
    String id = "foo";
    String corpusName = "cName";
    File corpusFile = new File("src/test/resources/speech_to_text/corpus-text.txt");

    server.enqueue(new MockResponse().addHeader(CONTENT_TYPE, HttpMediaType.APPLICATION_JSON).setBody("{}"));

    service.addCorpus(id, corpusName, corpusFile, true).execute();
    final RecordedRequest request = server.takeRequest();

    assertEquals("POST", request.getMethod());
    assertEquals(String.format(PATH_CORPUS, id, corpusName) + "?allow_overwrite=true", request.getPath());
    assertEquals(corpusFile.length(), request.getBodySize());
  }
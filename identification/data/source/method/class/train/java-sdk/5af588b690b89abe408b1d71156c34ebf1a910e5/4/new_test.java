@Test
  public void testAddCorpus() throws InterruptedException, IOException {
    String id = "foo";
    String corpusName = "cName";
    File corpusFile = new File("src/test/resources/speech_to_text/corpus-text.txt");
    String corpusFileText = new String(Files.readAllBytes(Paths.get(corpusFile.toURI())));

    server.enqueue(new MockResponse().addHeader(CONTENT_TYPE, HttpMediaType.APPLICATION_JSON).setBody("{}"));

    AddCorpusOptions addOptions = new AddCorpusOptions.Builder()
        .customizationId(id)
        .corpusName(corpusName)
        .corpusFile(corpusFile)
        .corpusFileContentType(HttpMediaType.TEXT_PLAIN)
        .allowOverwrite(true)
        .build();
    service.addCorpus(addOptions).execute();
    final RecordedRequest request = server.takeRequest();

    assertEquals("POST", request.getMethod());
    assertEquals(String.format(PATH_CORPUS, id, corpusName) + "?allow_overwrite=true", request.getPath());
    assertTrue(request.getBody().readUtf8().contains(corpusFileText));
  }
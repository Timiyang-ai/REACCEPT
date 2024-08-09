@Test
  public void testUpdateClassifier() throws IOException, InterruptedException {
    Classifier mockResponse = loadFixture(FIXTURE_CLASSIFIER, Classifier.class);

    server.enqueue(new MockResponse().setBody(mockResponse.toString()));

    // execute request
    File images = new File(IMAGE_FILE);
    String class1 = "class1";
    String classifierId = "foo123";

    UpdateClassifierOptions options = new UpdateClassifierOptions.Builder(classifierId).addClass(class1, images)
        .build();

    Classifier serviceResponse = service.updateClassifier(options).execute();

    // first request
    String path = String.format(PATH_CLASSIFIER, classifierId);
    RecordedRequest request = server.takeRequest();
    path += "?" + VERSION_DATE + "=2016-05-20&api_key=" + API_KEY;

    assertEquals(path, request.getPath());
    assertEquals("POST", request.getMethod());
    String body = request.getBody().readUtf8();

    String contentDisposition
        = "Content-Disposition: form-data; name=\"class1_positive_examples\"; filename=\"test.zip\"";
    assertTrue(body.contains(contentDisposition));
    assertTrue(!body.contains("Content-Disposition: form-data; name=\"name\""));
    assertEquals(serviceResponse, mockResponse);
  }
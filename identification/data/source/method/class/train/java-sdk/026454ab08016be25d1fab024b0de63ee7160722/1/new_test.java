@Test
  public void testCreateClassifier() throws IOException, InterruptedException {
    VisualClassifier mockResponse = loadFixture(FIXTURE_CLASSIFIER, VisualClassifier.class);
    
    server.enqueue(new MockResponse().setBody(mockResponse.toString()));

    // execute request
    File images = new File(IMAGE_FILE);
    String class1 = "class1"; 
    ClassifierOptions options = new ClassifierOptions.Builder()
        .classifierName(class1)
        .addClass(class1, images)
        .negativeExamples(images)
        .build();
    
    VisualClassifier serviceResponse = service.createClassifier(options).execute();

    // first request
    RecordedRequest request = server.takeRequest();
    String path = PATH_CLASSIFIERS + "?" +
        VERSION_DATE + "=" + VisualRecognition.VERSION_DATE_2016_05_19 + 
        "&api_key=" + API_KEY;
    
    assertEquals(path, request.getPath());
    assertEquals("POST", request.getMethod());
    String body = request.getBody().readUtf8();
    
    String contentDisposition = "Content-Disposition: form-data; name=\"class1_positive_examples\"; filename=\"test.zip\"";
    assertTrue(body.contains(contentDisposition));
    assertTrue(body.contains("Content-Disposition: form-data; name=\"name\""));
    assertEquals(serviceResponse, mockResponse);
  }
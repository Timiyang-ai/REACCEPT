@Test
  public void testDetectFaces() throws IOException, InterruptedException {
    DetectedFaces mockResponse = loadFixture(FIXTURE_FACES, DetectedFaces.class);

    server.enqueue(new MockResponse().setBody(mockResponse.toString()));

    // execute request
    File images = new File(IMAGE_FILE);
    DetectFacesOptions options = new DetectFacesOptions.Builder().imagesFile(images).build();

    DetectedFaces serviceResponse = service.detectFaces(options).execute();

    // first request
    RecordedRequest request = server.takeRequest();
    String path = PATH_DETECT_FACES + "?" + VERSION_DATE + "=" + VisualRecognition.VERSION_DATE_2016_05_20 + "&api_key="
        + API_KEY;

    assertEquals(path, request.getPath());
    assertEquals("POST", request.getMethod());
    assertEquals(serviceResponse, mockResponse);
    String contentDisposition = "Content-Disposition: form-data; name=\"images_file\"; filename=\"test.zip\"";
    String body = request.getBody().readUtf8();
    assertTrue(body.contains(contentDisposition));
  }
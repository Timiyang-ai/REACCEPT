@Test
  public void testGetClassifier() {

    System.out.println(GsonSingleton.getGson().toJson(""));
    final Classifier response = new Classifier();
    response.setId("testId");
    response.setStatus(Classifier.Status.AVAILABLE);
    response.setUrl("http://gateway.watson.net/");
    response
        .setStatusDescription("The classifier instance is now available and is ready to take classifier requests.");

    mockServer.when(request().withPath(CLASSIFIERS_PATH + "/" + classifierId)).respond(
        response().withHeaders(
            new Header(HttpHeaders.Names.CONTENT_TYPE, HttpMediaType.APPLICATION_JSON)).withBody(
            GsonSingleton.getGson().toJson(response)));

    final Classifier c = service.getClassifier(classifierId);
    assertNotNull(c);
    assertEquals(c, response);

  }
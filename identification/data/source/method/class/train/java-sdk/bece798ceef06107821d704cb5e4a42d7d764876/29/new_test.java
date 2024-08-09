@Test
  public void testGetClassifiers() {

    final Map<String, Object> response = new HashMap<String, Object>();
    final List<Classifier> classifiersResponse = new ArrayList<Classifier>();

    final Classifier c = new Classifier();
    c.setId("testId");
    c.setStatus(Classifier.Status.AVAILABLE);
    c.setUrl("http://gateway.watson.net/");
    c.setStatusDescription("The classifier instance is now available and is ready to take classifier requests.");
    classifiersResponse.add(c);

    final Classifier c1 = new Classifier();
    c1.setId("testId1");
    c1.setStatus(Classifier.Status.AVAILABLE);
    c1.setUrl("http://gateway.watson.net/");
    c1.setStatusDescription("The classifier instance is now available and is ready to take classifier requests.");
    classifiersResponse.add(c1);



    response.put("classifiers", classifiersResponse);

    mockServer.when(request().withPath(CLASSIFIERS_PATH)).respond(
        response().withHeaders(
            new Header(HttpHeaders.Names.CONTENT_TYPE, HttpMediaType.APPLICATION_JSON)).withBody(
            GsonSingleton.getGson().toJson(response)));


    final Classifiers classifiers = service.getClassifiers();
    assertNotNull(classifiers.getClassifiers());
    assertFalse(classifiers.getClassifiers().isEmpty());
    assertFalse(classifiers.getClassifiers().contains(classifiersResponse));

  }
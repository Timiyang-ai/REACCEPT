@Test
  public void createClassifier() throws Exception {
    final Classifier createdClassifier = new Classifier();
    createdClassifier.setId("testId2");
    createdClassifier.setStatus(Classifier.Status.AVAILABLE);
    createdClassifier.setUrl("http://gateway.watson.net/");

    mockServer.when(request().withMethod("POST").withPath(CLASSIFIERS_PATH)).respond(
        response().withHeaders(
            new Header(HttpHeaders.Names.CONTENT_TYPE, HttpMediaType.APPLICATION_JSON)).withBody(
            createdClassifier.toString()));

    final List<TrainingData> training = new ArrayList<TrainingData>();
    training.add(new TrainingData().withText("text1").withClasses("k1"));
    final Classifier classifier = service.createClassifier(null, null, training);

    assertEquals(createdClassifier.toString(), classifier.toString());
  }
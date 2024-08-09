@Test
  public void testGetClassifier() throws InterruptedException {
    server.enqueue(jsonResponse(classifier));
    GetClassifierOptions getOptions = new GetClassifierOptions.Builder()
        .classifierId(classifierId)
        .build();
    final Classifier response = service.getClassifier(getOptions).execute();
    final RecordedRequest request = server.takeRequest();

    assertEquals(CLASSIFIERS_PATH + "/" + classifierId, request.getPath());
    assertEquals(classifier, response);
  }
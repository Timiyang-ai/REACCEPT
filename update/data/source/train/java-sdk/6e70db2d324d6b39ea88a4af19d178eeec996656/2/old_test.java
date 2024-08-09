@Test
  public void testGetClassifier() throws InterruptedException {
    server.enqueue(jsonResponse(classifier));
    final Classifier response = service.getClassifier(classifierId).execute();
    final RecordedRequest request = server.takeRequest();

    assertEquals(CLASSIFIERS_PATH + "/" + classifierId, request.getPath());
    assertEquals(classifier, response);
  }
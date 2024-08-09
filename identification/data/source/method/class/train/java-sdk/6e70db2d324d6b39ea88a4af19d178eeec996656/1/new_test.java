@Test
  public void testGetClassifiers() throws InterruptedException {
    server.enqueue(jsonResponse(classifiers));
    final ClassifierList response = service.listClassifiers().execute();
    final RecordedRequest request = server.takeRequest();

    assertEquals(CLASSIFIERS_PATH, request.getPath());
    assertEquals(classifiers, response);
  }
@Test
  public void testCreateClassifier() throws InterruptedException {
    server.enqueue(jsonResponse(classifier));
    final Classifier response = service.createClassifier(classifierId, "en",
        new File("src/test/resources/natural_language_classifier/weather_data_train.csv")).execute();
    final RecordedRequest request = server.takeRequest();

    assertEquals(CLASSIFIERS_PATH, request.getPath());
    assertEquals(classifier, response);
  }
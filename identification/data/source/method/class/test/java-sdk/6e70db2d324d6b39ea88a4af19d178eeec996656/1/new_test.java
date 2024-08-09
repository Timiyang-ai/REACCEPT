@Test
  public void testCreateClassifier() throws InterruptedException, FileNotFoundException {
    server.enqueue(jsonResponse(classifier));
    File metadata = new File(RESOURCE + "metadata.json");
    File trainingData = new File(RESOURCE + "weather_data_train.csv");
    CreateClassifierOptions createOptions = new CreateClassifierOptions.Builder()
        .metadata(metadata)
        .trainingData(trainingData)
        .trainingDataFilename("weather_data_train.csv")
        .build();
    final Classifier response = service.createClassifier(createOptions).execute();
    final RecordedRequest request = server.takeRequest();

    assertEquals(CLASSIFIERS_PATH, request.getPath());
    assertEquals(classifier, response);
  }
@Test
  public void testCreateAcousticModel() throws InterruptedException, FileNotFoundException {
    AcousticModel model = loadFixture("src/test/resources/speech_to_text/acoustic-model.json", AcousticModel.class);

    server.enqueue(
        new MockResponse().addHeader(CONTENT_TYPE, HttpMediaType.APPLICATION_JSON).setBody(GSON.toJson(model)));

    CreateAcousticModelOptions createOptions = new CreateAcousticModelOptions.Builder()
        .name(model.getName())
        .baseModelName(model.getBaseModelName())
        .description(model.getDescription())
        .build();
    AcousticModel result = service.createAcousticModel(createOptions).execute();
    final RecordedRequest request = server.takeRequest();

    assertEquals("POST", request.getMethod());
    assertEquals(PATH_ACOUSTIC_CUSTOMIZATIONS, request.getPath());
    assertEquals(result.toString(), model.toString());
  }
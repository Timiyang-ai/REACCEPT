@Test
  public void testCreateLanguageModel() throws InterruptedException, FileNotFoundException {
    LanguageModel model =
        loadFixture("src/test/resources/speech_to_text/customization.json", LanguageModel.class);

    server.enqueue(
        new MockResponse().addHeader(CONTENT_TYPE, HttpMediaType.APPLICATION_JSON).setBody(GSON.toJson(model)));

    CreateLanguageModel newModel = new CreateLanguageModel.Builder()
        .name(model.getName())
        .baseModelName("en-GB_BroadbandModel")
        .description(model.getDescription())
        .build();
    CreateLanguageModelOptions createOptions = new CreateLanguageModelOptions.Builder()
        .createLanguageModel(newModel)
        .build();
    LanguageModel result = service.createLanguageModel(createOptions).execute();
    final RecordedRequest request = server.takeRequest();

    assertEquals("POST", request.getMethod());
    assertEquals(PATH_CUSTOMIZATIONS, request.getPath());
    assertEquals(result.toString(), model.toString());
  }
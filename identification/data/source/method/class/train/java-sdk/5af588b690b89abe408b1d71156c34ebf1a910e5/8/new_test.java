@Test
  public void testGetModel() throws Exception {
    final SpeechModel speechModel = new SpeechModel();
    speechModel.setName("not-a-real-Model");
    speechModel.setRate(8000);

    final MockResponse mockResponse =
        new MockResponse().addHeader(CONTENT_TYPE, HttpMediaType.APPLICATION_JSON).setBody(GSON.toJson(speechModel));

    server.enqueue(mockResponse);
    GetModelOptions getOptionsString = new GetModelOptions.Builder()
        .modelId("not-a-real-Model")
        .build();
    SpeechModel model = service.getModel(getOptionsString).execute();
    RecordedRequest request = server.takeRequest();

    assertNotNull(model);
    assertEquals(model, speechModel);
    assertEquals(PATH_MODELS + "/" + speechModel.getName(), request.getPath());

    server.enqueue(mockResponse);
    GetModelOptions getOptionsGetter = new GetModelOptions.Builder()
        .modelId(speechModel.getName())
        .build();
    model = service.getModel(getOptionsGetter).execute();
    request = server.takeRequest();

    assertNotNull(model);
    assertEquals(model, speechModel);
    assertEquals(PATH_MODELS + "/" + speechModel.getName(), request.getPath());

    TestUtils.assertNoExceptionsOnGetters(model);
  }
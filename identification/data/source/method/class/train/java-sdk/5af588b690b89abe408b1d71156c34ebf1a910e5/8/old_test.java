@Test
  public void testGetModel() throws Exception {
    final SpeechModel speechModel = new SpeechModel("not-a-real-Model");
    speechModel.setRate(8000);

    final MockResponse mockResponse =
        new MockResponse().addHeader(CONTENT_TYPE, HttpMediaType.APPLICATION_JSON).setBody(GSON.toJson(speechModel));

    server.enqueue(mockResponse);
    SpeechModel model = service.getModel("not-a-real-Model").execute();
    RecordedRequest request = server.takeRequest();

    assertNotNull(model);
    assertEquals(model, speechModel);
    assertEquals(PATH_MODELS + "/" + speechModel.getName(), request.getPath());

    server.enqueue(mockResponse);
    model = service.getModel(speechModel.getName()).execute();
    request = server.takeRequest();

    assertNotNull(model);
    assertEquals(model, speechModel);
    assertEquals(PATH_MODELS + "/" + speechModel.getName(), request.getPath());

    TestUtils.assertNoExceptionsOnGetters(model);
  }
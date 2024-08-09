@Test
  public void testGetModel() {

    final SpeechModel speechModel = new SpeechModel("not-a-real-Model");
    speechModel.setRate(8000);

    mockServer.when(request().withPath(GET_MODELS_PATH + "/" + speechModel.getName())).respond(
        response().withHeaders(
            new Header(HttpHeaders.Names.CONTENT_TYPE, HttpMediaType.APPLICATION_JSON)).withBody(
            GsonSingleton.getGson().toJson(speechModel)));


    SpeechModel model = service.getModel("not-a-real-Model");
    Assert.assertNotNull(model);
    Assert.assertEquals(model, speechModel);

    model = service.getModel(speechModel);
    Assert.assertNotNull(model);
    Assert.assertEquals(model, speechModel);

    try {
      TestUtils.assertNoExceptionsOnGetters(model);
    } catch (final Exception e) {
      Assert.fail(e.getMessage());
    }
  }
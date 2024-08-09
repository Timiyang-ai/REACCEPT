@Test
  public void testGetModels() {

    final SpeechModelSet speechModelSet = new SpeechModelSet();

    final SpeechModel speechModel = new SpeechModel("not-a-real-Model");
    speechModel.setRate(8000);

    final SpeechModel speechModel1 = new SpeechModel("not-a-real-Model1");
    speechModel1.setRate(1600);

    final SpeechModel speechModel2 = new SpeechModel("not-a-real-Model2");
    speechModel2.setRate(8000);

    final List<SpeechModel> speechModels = new ArrayList<SpeechModel>();
    speechModels.add(speechModel);
    speechModels.add(speechModel1);
    speechModels.add(speechModel2);

    speechModelSet.setModels(speechModels);

    mockServer.when(request().withPath(GET_MODELS_PATH)).respond(
        response().withHeaders(
            new Header(HttpHeaders.Names.CONTENT_TYPE, HttpMediaType.APPLICATION_JSON)).withBody(
            GsonSingleton.getGsonWithoutPrettyPrinting().toJson(speechModelSet)));
    final List<SpeechModel> models = service.getModels().execute();
    Assert.assertNotNull(models);
    Assert.assertFalse(models.isEmpty());
    Assert.assertEquals(models, speechModelSet.getModels());
  }
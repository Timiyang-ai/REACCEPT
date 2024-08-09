@Test
  public void testGetModels() {

    final Map<String, Object> response = new HashMap<String, Object>();
    final List<TranslationModel> tsModels = new ArrayList<TranslationModel>();

    final TranslationModel tm = new TranslationModel();
    tm.setModelId("not-a-real-model");
    tm.setSource("en");
    tm.setBaseModelId("en-es");
    tm.setDomain("news");
    tm.setCustomizable(false);
    tm.setDefaultModel(false);
    tm.setOwner("not-a-real-user");
    tm.setStatus("error");
    tm.setName("custom-english-to-spanish");
    tsModels.add(tm);

    final TranslationModel tm1 = new TranslationModel();
    tm1.setModelId("not-a-real-model-2");
    tm1.setSource("en");
    tm1.setBaseModelId("es");
    tm1.setDomain("news");
    tm1.setCustomizable(false);
    tm1.setDefaultModel(false);
    tm1.setOwner("not-a-real-user");
    tm1.setStatus("error");
    tm1.setName("custom-english-to-spanish");
    tsModels.add(tm1);

    final TranslationModel tm2 = new TranslationModel();
    tm2.setModelId("ar-en");
    tm2.setSource("en");
    tm2.setBaseModelId("");
    tm2.setDomain("news");
    tm2.setCustomizable(true);
    tm2.setDefaultModel(true);
    tm2.setOwner("");
    tm2.setStatus("available");
    tm2.setName("custom-english-to-spanish");
    tsModels.add(tm2);

    response.put("models", tsModels);

    mockServer.when(request().withPath(GET_MODELS_PATH)).respond(
        response().withHeaders(
            new Header(HttpHeaders.Names.CONTENT_TYPE, HttpMediaType.APPLICATION_JSON)).withBody(
            GsonSingleton.getGson().toJson(response)));

    final List<TranslationModel> models = service.getModels();

    mockServer.verify(request().withPath(GET_MODELS_PATH), VerificationTimes.exactly(1));
    assertNotNull(models);

    assertNotNull(models);
    assertFalse(models.isEmpty());
    assertNotNull(models.containsAll(tsModels));

  }
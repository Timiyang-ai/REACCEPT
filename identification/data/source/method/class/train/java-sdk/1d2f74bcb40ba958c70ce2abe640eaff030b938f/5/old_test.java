@Test
  public void testGetIdentifiableLanguages() {

    mockServer.when(request().withPath(IDENTIFIABLE_LANGUAGES_PATH)).respond(
        response().withHeader(APPLICATION_JSON).withBody(
            GsonSingleton.getGsonWithoutPrettyPrinting().toJson(identifiableLanguages)));

    List<IdentifiableLanguage> languages = service.getIdentifiableLanguages().execute();

    assertEquals(GsonSingleton.getGsonWithoutPrettyPrinting().toJson(languages),
        GsonSingleton.getGsonWithoutPrettyPrinting().toJson(identifiableLanguages.getLanguages()));
  }
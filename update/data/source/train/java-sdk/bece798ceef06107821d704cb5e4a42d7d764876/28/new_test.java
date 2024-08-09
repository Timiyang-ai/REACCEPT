@Test
  public void testIdentify() {

    final Map<String, Object> response = new HashMap<String, Object>();
    final List<IdentifiedLanguage> langs = new ArrayList<IdentifiedLanguage>();
    langs.add(new IdentifiedLanguage("en", 0.877159));
    langs.add(new IdentifiedLanguage("af", 0.0752636));
    langs.add(new IdentifiedLanguage("nl", 0.0301593));

    response.put("languages", langs);

    mockServer.when(request().withMethod("POST").withPath(IDENTITY_PATH).withBody(text)).respond(
        response().withHeaders(
            new Header(HttpHeaders.Names.CONTENT_TYPE, HttpMediaType.APPLICATION_JSON)).withBody(
            GsonSingleton.getGson().toJson(response)));

    final List<IdentifiedLanguage> identifiedLanguages = service.identify(text);
    assertNotNull(identifiedLanguages);
    assertFalse(identifiedLanguages.isEmpty());
    assertNotNull(identifiedLanguages.containsAll(langs));
  }
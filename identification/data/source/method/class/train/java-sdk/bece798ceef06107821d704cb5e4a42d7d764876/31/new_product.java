public List<IdentifiedLanguage> identify(final String text) {
    final Request request =
        RequestBuilder.post("/v2/identify").withBodyContent(text, HttpMediaType.TEXT_PLAIN)
            .withHeader(HttpHeaders.ACCEPT, HttpMediaType.APPLICATION_JSON).build();

    final Response response = execute(request);
    final JsonObject jsonObject = ResponseUtil.getJsonObject(response);
    final List<IdentifiedLanguage> identifiedLanguages =
        GsonSingleton.getGson().fromJson(jsonObject.get(LANGUAGES), translationModelListType);
    return identifiedLanguages;
  }
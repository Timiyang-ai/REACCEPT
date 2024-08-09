@SuppressWarnings("unchecked")
  public List<IdentifiedLanguage> identify(final String text) {
    final Request request = RequestBuilder.post(PATH_IDENTIFY)
        .withHeader(HttpHeaders.ACCEPT, HttpMediaType.APPLICATION_JSON)
        .withBodyContent(text, HttpMediaType.TEXT_PLAIN).build();

    final LanguageList languages = executeRequest(request, LanguageList.class);

    return (List<IdentifiedLanguage>) (List<?>) languages.getLanguages();
  }
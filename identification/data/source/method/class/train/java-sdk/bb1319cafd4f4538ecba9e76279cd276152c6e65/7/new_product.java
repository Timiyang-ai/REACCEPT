public ServiceCall<List<IdentifiedLanguage>> identify(final String text) {
    final Request request = RequestBuilder.post(PATH_IDENTIFY)
            .withHeader(HttpHeaders.ACCEPT, HttpMediaType.APPLICATION_JSON)
            .withBodyContent(text, HttpMediaType.TEXT_PLAIN).build3();

    return  createServiceCall(createCall(request), ResponseUtil.getLanguageListIdentifier());
  }
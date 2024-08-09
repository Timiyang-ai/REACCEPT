public ServiceCall<Corpus> getCorpus(String customizationId, String corpusName) {
    Validator.notNull(customizationId, "customizationId cannot be null");
    Validator.notNull(corpusName, "corpusName cannot be null");
    RequestBuilder requestBuilder = RequestBuilder.get(String.format(PATH_CORPUS, customizationId, corpusName));
    return createServiceCall(requestBuilder.build(), ResponseConverterUtils.getObject(Corpus.class));
  }
public ServiceCall<Corpus> getCorpus(GetCorpusOptions getCorpusOptions) {
    Validator.notNull(getCorpusOptions, "getCorpusOptions cannot be null");
    RequestBuilder builder = RequestBuilder.get(String.format("/v1/customizations/%s/corpora/%s", getCorpusOptions
        .customizationId(), getCorpusOptions.corpusName()));
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(Corpus.class));
  }
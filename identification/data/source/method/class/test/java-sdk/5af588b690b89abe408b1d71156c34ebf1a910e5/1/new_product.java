public ServiceCall<Void> deleteCorpus(DeleteCorpusOptions deleteCorpusOptions) {
    Validator.notNull(deleteCorpusOptions, "deleteCorpusOptions cannot be null");
    RequestBuilder builder = RequestBuilder.delete(String.format("/v1/customizations/%s/corpora/%s", deleteCorpusOptions
        .customizationId(), deleteCorpusOptions.corpusName()));
    return createServiceCall(builder.build(), ResponseConverterUtils.getVoid());
  }
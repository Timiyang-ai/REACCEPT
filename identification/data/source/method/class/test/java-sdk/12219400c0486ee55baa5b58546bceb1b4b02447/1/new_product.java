public ServiceCall<Translation> getWord(GetWordOptions getWordOptions) {
    Validator.notNull(getWordOptions, "getWordOptions cannot be null");
    RequestBuilder builder = RequestBuilder.get(String.format("/v1/customizations/%s/words/%s", getWordOptions
        .customizationId(), getWordOptions.word()));
    return createServiceCall(builder.build(), ResponseConverterUtils.getObject(Translation.class));
  }
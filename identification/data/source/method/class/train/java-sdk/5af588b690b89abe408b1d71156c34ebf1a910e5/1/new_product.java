public ServiceCall<Void> addWords(AddWordsOptions addWordsOptions) {
    Validator.notNull(addWordsOptions, "addWordsOptions cannot be null");
    RequestBuilder builder = RequestBuilder.post(String.format("/v1/customizations/%s/words", addWordsOptions
        .customizationId()));
    builder.header("Content-Type", addWordsOptions.contentType());
    if (addWordsOptions.contentType().equalsIgnoreCase(AddWordsOptions.ContentType.APPLICATION_JSON)) {
      builder.bodyJson(GsonSingleton.getGson().toJsonTree(addWordsOptions.customWords()).getAsJsonObject());
    } else {
      builder.bodyContent(addWordsOptions.body(), addWordsOptions.contentType());
    }
    return createServiceCall(builder.build(), ResponseConverterUtils.getVoid());
  }
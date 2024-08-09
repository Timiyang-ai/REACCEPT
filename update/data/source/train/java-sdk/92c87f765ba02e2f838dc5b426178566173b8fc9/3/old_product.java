public ServiceCall<Void> addWord(AddWordOptions addWordOptions) {
    Validator.notNull(addWordOptions, "addWordOptions cannot be null");
    RequestBuilder builder = RequestBuilder.put(String.format("/v1/customizations/%s/words/%s", addWordOptions
        .customizationId(), addWordOptions.wordName()));
    builder.header("Content-Type", addWordOptions.contentType());
    if (addWordOptions.contentType().equalsIgnoreCase(AddWordOptions.ContentType.APPLICATION_JSON)) {
      builder.bodyJson(GsonSingleton.getGson().toJsonTree(addWordOptions.customWord()).getAsJsonObject());
    }
    return createServiceCall(builder.build(), ResponseConverterUtils.getVoid());
  }
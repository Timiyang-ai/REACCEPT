public ServiceCall<Void> addWord(AddWordOptions addWordOptions) {
    Validator.notNull(addWordOptions, "addWordOptions cannot be null");
    RequestBuilder builder = RequestBuilder.put(String.format("/v1/customizations/%s/words/%s", addWordOptions
        .customizationId(), addWordOptions.wordName()));
    final JsonObject contentJson = new JsonObject();
    if (addWordOptions.word() != null) {
      contentJson.addProperty("word", addWordOptions.word());
    }
    if (addWordOptions.soundsLike() != null) {
      contentJson.add("sounds_like", GsonSingleton.getGson().toJsonTree(addWordOptions.soundsLike()));
    }
    if (addWordOptions.displayAs() != null) {
      contentJson.addProperty("display_as", addWordOptions.displayAs());
    }
    builder.bodyJson(contentJson);
    return createServiceCall(builder.build(), ResponseConverterUtils.getVoid());
  }
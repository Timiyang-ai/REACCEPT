public ServiceCall<Void> addWords(AddWordsOptions addWordsOptions) {
    Validator.notNull(addWordsOptions, "addWordsOptions cannot be null");
    List<String> pathSegments = Arrays.asList("v1/customizations", "words");
    List<String> pathParameters = Arrays.asList(addWordsOptions.customizationId());
    RequestBuilder builder = RequestBuilder.post(RequestBuilder.constructHttpUrl(getEndPoint(), pathSegments,
        pathParameters));
    final JsonObject contentJson = new JsonObject();
    contentJson.add("words", GsonSingleton.getGson().toJsonTree(addWordsOptions.words()));
    builder.bodyJson(contentJson);
    return createServiceCall(builder.build(), ResponseConverterUtils.getVoid());
  }
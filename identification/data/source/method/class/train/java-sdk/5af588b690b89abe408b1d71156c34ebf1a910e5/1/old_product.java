public ServiceCall<Void> addWords(String customizationId, Word... words) {
    Validator.notNull(customizationId, "customizationId cannot be null");
    Validator.notNull(words, "words cannot be null");
    Validator.isTrue(words.length > 0, "words cannot be empty");

    RequestBuilder requestBuilder = RequestBuilder.post(String.format(PATH_WORDS, customizationId));

    Map<String, Object> wordsAsMap = new HashMap<String, Object>();
    wordsAsMap.put(WORDS, words);

    requestBuilder.bodyContent(GSON.toJson(wordsAsMap), HttpMediaType.APPLICATION_JSON);

    return createServiceCall(requestBuilder.build(), ResponseConverterUtils.getVoid());
  }
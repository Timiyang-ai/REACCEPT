public ServiceCall<WordData> getWord(String customizationId, String wordName) {
    Validator.notNull(customizationId, "customizationId cannot be null");
    Validator.notNull(wordName, "wordName cannot be null");

    RequestBuilder requestBuilder = RequestBuilder.get(String.format(PATH_WORD, customizationId, wordName));
    return createServiceCall(requestBuilder.build(), ResponseConverterUtils.getObject(WordData.class));
  }
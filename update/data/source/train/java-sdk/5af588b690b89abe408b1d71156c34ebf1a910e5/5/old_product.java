public ServiceCall<Void> deleteWord(String customizationId, String wordName) {
    Validator.notNull(customizationId, "customizationId cannot be null");
    Validator.notNull(wordName, "words cannot be null");

    RequestBuilder requestBuilder = RequestBuilder.delete(String.format(PATH_WORD, customizationId, wordName));
    return createServiceCall(requestBuilder.build(), ResponseConverterUtils.getVoid());
  }
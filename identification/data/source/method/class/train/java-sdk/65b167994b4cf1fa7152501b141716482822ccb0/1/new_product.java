public ServiceCall<List<WordData>> getWords(String customizationId, Word.Type type) {
    Validator.notNull(customizationId, "customizationId cannot be null");
    RequestBuilder requestBuilder = RequestBuilder.get(String.format(PATH_WORDS, customizationId));
    if (type != null) {
      requestBuilder.query(WORD_TYPE, type.toString().toLowerCase());
    }

    ResponseConverter<List<WordData>> converter = ResponseConverterUtils.getGenericObject(TYPE_WORDS, WORDS);
    return createServiceCall(requestBuilder.build(), converter);
  }